package com.edsusantoo.bismillah.moviecatalogue.ui.detailmovie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DetailMovieViewModel extends AndroidViewModel {
    private MoviesRepository repository;

    private MutableLiveData<Favorites> movieFavorite = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> messageError = new MutableLiveData<>();
    private MutableLiveData<String> messageSuccess = new MutableLiveData<>();

    public DetailMovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }

    LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    LiveData<String> getMessageError() {
        return messageError;
    }

    LiveData<String> getMessageSuccess() {
        return messageSuccess;
    }

    LiveData<Favorites> getMovieFavorite() {
        return movieFavorite;
    }

    void getMovieFavorite(final int movieId) {
        repository.getMovieFavorites(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Favorites>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Favorites favorites) {
                        movieFavorite.setValue(favorites);
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


    void insertMovie(final Movie movie) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                repository.insertMovie(movie);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        messageSuccess.setValue("Successful add movie");
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageError.setValue(e.getMessage());
                    }
                });
    }

    void insertFavorite(final Favorites favorites) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                repository.insertFavorite(favorites);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        messageSuccess.setValue("Successful add favorite");
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageError.setValue(e.getMessage());
                    }
                });
    }

    void deleteMovie(final Movie movie) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                repository.deleteMovie(movie);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        messageSuccess.setValue("Movie Berhasil Dihapus");
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageError.setValue(e.getMessage());
                    }
                });
    }

    void deleteFavorite(final Favorites favorites) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                repository.deleteFavorite(favorites);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        messageSuccess.setValue("Favorite Berhasil Dihapus");
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageError.setValue(e.getMessage());
                    }
                });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestroy();
    }
}
