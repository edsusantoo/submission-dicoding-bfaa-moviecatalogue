package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;

public class MoviesViewModel extends AndroidViewModel {
    private MoviesRepository repository;

    public MoviesViewModel(Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }

    LiveData<MovieResponse> getMovies(String language) {
        return repository.getMovie(BuildConfig.API_KEY, language);
    }

    String getLanguage() {
        return repository.getLanguage();
    }

}
