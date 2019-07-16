package com.edsusantoo.bismillah.moviecatalogue.ui.settings;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;

public class SettingsViewModel extends AndroidViewModel {
    private MoviesRepository repository;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
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
