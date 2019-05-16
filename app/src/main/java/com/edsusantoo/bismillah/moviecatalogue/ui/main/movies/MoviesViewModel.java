package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiObserver;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesViewModel extends AndroidViewModel {
    private MoviesRepository repository;
    private MutableLiveData<MovieResponse> dataMovies = new MutableLiveData<>();

    public MoviesViewModel(Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }


    MutableLiveData<MovieResponse> getDataMovies() {
        return dataMovies;
    }

    void getMovies(String language) {
        if (dataMovies.getValue() == null) {
            repository.getMovie(BuildConfig.API_KEY, language)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiObserver<MovieResponse>(repository.getCompositeDisposable()) {
                        @Override
                        public void onSuccess(MovieResponse response) {
                            dataMovies.setValue(response);
                        }

                        @Override
                        public void onFailure(String message) {
                            dataMovies.setValue(null);
                        }
                    });
        }
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
