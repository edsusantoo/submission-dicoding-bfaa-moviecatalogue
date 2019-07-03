package com.edsusantoo.bismillah.moviecatalogue.ui.widget.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;
import com.edsusantoo.bismillah.moviecatalogue.ui.widget.FavoriteWidget;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final List<Movie> mWidgetItems = new ArrayList<>();
    private final Context mContext;

    public StackRemoteViewsFactory(Context context) {
        this.mContext = context;
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        getMovieFavorite();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_favorite);
        try {
            Bitmap bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(mWidgetItems.get(position).getMoviePhoto())
                    .submit(512, 512)
                    .get();

            rv.setImageViewBitmap(R.id.img_widget_movie, bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rv.setTextViewText(R.id.tv_widget_title_movie, mWidgetItems.get(position).getMovieName());

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.img_widget_movie, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private void getMovieFavorite() {
        MoviesRepository repository = new MoviesRepository(mContext);
        repository.getAllFavorite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Favorites>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Favorites> favorites) {
                        for (Favorites data : favorites) {
                            getMovie(data.getMovieId());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void getMovie(final int movieId) {
        final MoviesRepository repository = new MoviesRepository(mContext);
        repository.getMovie(movieId, Constant.TYPE_MOVIE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Movie> movies) {
                        if (movies.size() != 0) {
                            mWidgetItems.addAll(movies);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
