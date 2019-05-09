package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.ResultsItem;

import java.util.List;

interface MoviesView {
    void showLoading();

    void hideLoading();

    void showListMovies(List<ResultsItem> data);

    void onMovieEmpty();

    void onErrorConnection(String massage);

}
