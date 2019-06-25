package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.tvshows;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavoriteTvShowsViewModel extends AndroidViewModel {
    private MoviesRepository repository;


    private MutableLiveData<List<Favorites>> dataFavorite = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> dataMovie = new MutableLiveData<>();
    private MutableLiveData<String> messageError = new MutableLiveData<>();

    public FavoriteTvShowsViewModel(@NonNull Application application) {
        super(application);

        repository = new MoviesRepository(application.getApplicationContext());
    }

    LiveData<List<Favorites>> getFavorite() {
        return dataFavorite;
    }

    LiveData<List<Movie>> getMovie() {
        return dataMovie;
    }

    void getMovieFavorite() {
        repository.getAllFavorite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Favorites>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Favorites> favorites) {
                        dataFavorite.setValue(favorites);
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageError.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void getMovie(int movieId, String type) {
        repository.getMovie(movieId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Movie> movies) {
                        dataMovie.setValue(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageError.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestroy();
    }

}
