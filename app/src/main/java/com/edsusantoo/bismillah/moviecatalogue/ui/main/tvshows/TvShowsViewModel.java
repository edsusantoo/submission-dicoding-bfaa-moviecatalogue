package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

public class TvShowsViewModel extends AndroidViewModel {
    private MoviesRepository repository;

    public TvShowsViewModel(Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }

    LiveData<TvShowResponse> getTvShow(String language) {
        return repository.getTvShow(BuildConfig.API_KEY, language);
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
