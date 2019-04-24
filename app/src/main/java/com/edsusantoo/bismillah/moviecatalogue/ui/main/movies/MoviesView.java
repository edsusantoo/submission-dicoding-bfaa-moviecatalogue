package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;

import com.edsusantoo.bismillah.moviecatalogue.data.Movie;

import java.util.List;

interface MoviesView {
    void showLoading();

    void hideLoading();

    void showListMovies(List<Movie> movies);

}
