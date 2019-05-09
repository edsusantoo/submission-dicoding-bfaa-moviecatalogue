package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.RetrofitConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class TvShowsPresenter {
    private TvShowsView view;

    TvShowsPresenter(TvShowsView view) {
        this.view = view;
    }

    void getTvMovie() {
        view.showLoading();
        RetrofitConfig.getApiServices()
                .getTvMovie(BuildConfig.API_KEY, "en-US")
                .enqueue(new Callback<TvShowResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getResults() != null) {
                                    view.showListTvShow(response.body().getResults());
                                } else {
                                    view.onMovieEmpty();
                                }
                            }
                        } else {
                            view.onErrorConnection("Not Successful");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                        view.hideLoading();
                        view.onErrorConnection(t.getMessage());
                    }
                });
    }
}
