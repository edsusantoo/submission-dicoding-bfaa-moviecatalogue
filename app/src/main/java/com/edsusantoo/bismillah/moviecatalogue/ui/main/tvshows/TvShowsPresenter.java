package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import android.content.Context;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiObserver;
import com.edsusantoo.bismillah.moviecatalogue.data.network.RetrofitConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class TvShowsPresenter {
    private TvShowsView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    TvShowsPresenter(TvShowsView view) {
        this.view = view;
    }

    void getTvMovie(String language) {
        view.showLoading();
        RetrofitConfig.getApiServices()
                .getTvMovie(BuildConfig.API_KEY, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<TvShowResponse>(compositeDisposable) {
                    @Override
                    public void onSuccess(TvShowResponse response) {
                        view.hideLoading();
                        if (response != null) {
                            if (response.getResults() != null) {
                                view.showListTvShow(response.getResults());
                            } else {
                                view.onMovieEmpty();
                            }
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        view.onErrorConnection(message);
                    }

                });
    }

    String getLanguage(Context context) {
        SharedPref sharedPref = new SharedPref(context);
        return sharedPref.getSharedPref().getString(Constant.PREF_LANGUAGE, "");
    }
}
