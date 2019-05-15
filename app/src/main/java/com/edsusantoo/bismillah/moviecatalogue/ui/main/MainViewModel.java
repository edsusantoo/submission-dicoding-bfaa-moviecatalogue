package com.edsusantoo.bismillah.moviecatalogue.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;

public class MainViewModel extends AndroidViewModel {
    private MoviesRepository repository;

    public MainViewModel(Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }

    String getLanguage() {
        return repository.getLanguage();
    }
}
