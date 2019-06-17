package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.movies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiObserver;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MoviesViewModel extends AndroidViewModel {
    private MoviesRepository repository;
    private MutableLiveData<MovieResponse> dataMovies = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> messageError = new MutableLiveData<>();
    private MutableLiveData<String> messageSuccess = new MutableLiveData<>();

    public MoviesViewModel(Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }


    LiveData<MovieResponse> getDataMovies() {
        return dataMovies;
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

    void getMovies(String language) {
        if (dataMovies.getValue() == null) {
            isLoading.setValue(true);
            repository.getMovie(BuildConfig.API_KEY, language)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiObserver<MovieResponse>(repository.getCompositeDisposable()) {
                        @Override
                        public void onSuccess(MovieResponse response) {
                            isLoading.setValue(false);
                            dataMovies.setValue(response);
                        }

                        @Override
                        public void onFailure(String message) {
                            isLoading.setValue(false);
                            dataMovies.setValue(null);
                        }
                    });
        }
    }

    void refreshMovies(String language) {
        isLoading.setValue(true);
        repository.getMovie(BuildConfig.API_KEY, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<MovieResponse>(repository.getCompositeDisposable()) {
                    @Override
                    public void onSuccess(MovieResponse response) {
                        isLoading.setValue(false);
                        dataMovies.setValue(response);
                    }

                    @Override
                    public void onFailure(String message) {
                        isLoading.setValue(false);
                        dataMovies.setValue(null);
                    }
                });
    }

    void insertMovie(final com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie movie) {
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

    void deleteMovie(final com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie movie) {
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

    String getLanguage() {
        return repository.getLanguage();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestroy();
    }
}
