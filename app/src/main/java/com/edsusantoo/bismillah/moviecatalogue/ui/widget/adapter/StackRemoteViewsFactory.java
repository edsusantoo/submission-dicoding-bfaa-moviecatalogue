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


//TODO: BELUM BISA REFRESH KETIKA ADA DATA BARU
public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<Movie> mWidgetItems = new ArrayList<>();
    private Context mContext;


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
        //clear jika ada isinya untuk mengatasi data yang ke add ulang
        if (mWidgetItems.size() != 0) {
            mWidgetItems.clear();
        }
        List<Favorites> favorites;
        MoviesRepository repository = new MoviesRepository(mContext);
        favorites = repository.getAllFavoriteWidget();
        for (Favorites data : favorites) {
            getMovie(data.getMovieId());
        }

    }

    private void getMovie(final int movieId) {
        final MoviesRepository repository = new MoviesRepository(mContext);
        mWidgetItems.addAll(repository.getMovieWidget(movieId, Constant.TYPE_MOVIE));
    }
}
