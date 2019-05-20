package com.edsusantoo.bismillah.moviecatalogue.ui.changelanguage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;

public class ChangeLanguageViewModel extends AndroidViewModel {
    private MoviesRepository repository;

    public ChangeLanguageViewModel(@NonNull Application application) {
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
