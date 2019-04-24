package com.edsusantoo.bismillah.moviecatalogue.main.movies;

import com.edsusantoo.bismillah.moviecatalogue.main.movies.model.Movie;

import java.util.ArrayList;

public interface MoviesView {
    void showLoading();

    void hideLoading();

    void showDaftarFilm(ArrayList<Movie> movies);

}
