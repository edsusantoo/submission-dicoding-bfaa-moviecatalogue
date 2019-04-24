package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import com.edsusantoo.bismillah.moviecatalogue.data.Movie;

import java.util.List;

interface TvShowsView {

    void showLoading();

    void hideLoading();

    void showListMovies(List<Movie> movies);
}
