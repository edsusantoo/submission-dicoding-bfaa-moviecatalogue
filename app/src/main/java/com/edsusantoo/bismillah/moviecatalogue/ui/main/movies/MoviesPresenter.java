package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;

import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.BuildConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.RetrofitConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MoviesPresenter {
    private MoviesView view;

    MoviesPresenter(MoviesView view) {
        this.view = view;
    }

    void getMovies(String language) {
        view.showLoading();
        RetrofitConfig.getApiServices().getMovie(
                BuildConfig.API_KEY,
                language
        ).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            view.showListMovies(response.body().getResults());
                        } else {
                            view.onMovieEmpty();
                        }
                    }
                } else {
                    view.onErrorConnection("Not Successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorConnection(t.getMessage());
            }
        });
    }
}
