package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiObserver;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TvShowsViewModel extends AndroidViewModel {
    private MoviesRepository repository;
    private MutableLiveData<TvShowResponse> dataTvShows = new MutableLiveData<>();

    public TvShowsViewModel(Application application) {
        super(application);
        repository = new MoviesRepository(application.getApplicationContext());
    }


    LiveData<TvShowResponse> getDataTvShows() {
        return dataTvShows;
    }

    void getTvShow(String language) {
        if (dataTvShows.getValue() == null) {
            repository.getTvShow(BuildConfig.API_KEY, language)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiObserver<TvShowResponse>(repository.getCompositeDisposable()) {
                        @Override
                        public void onSuccess(TvShowResponse response) {
                            dataTvShows.setValue(response);
                        }

                        @Override
                        public void onFailure(String message) {
                            dataTvShows.setValue(null);
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
