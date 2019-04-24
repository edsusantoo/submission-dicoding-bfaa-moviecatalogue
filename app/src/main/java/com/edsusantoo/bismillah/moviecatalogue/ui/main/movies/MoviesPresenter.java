package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;

import android.content.res.TypedArray;

import com.edsusantoo.bismillah.moviecatalogue.data.Movie;

import java.util.ArrayList;

class MoviesPresenter {
    private MoviesView view;

    MoviesPresenter(MoviesView view) {
        this.view = view;
    }

    void addMovie(String[] dataMovieName,
                  String[] dataMovieDate,
                  String[] dataMovieDescription,
                  String[] dataMovieRating,
                  String[] dataMovieRevenue,
                  TypedArray dataMoviePhoto) {
        view.showLoading();

        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < dataMovieName.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(dataMovieName[i]);
            movie.setDate(dataMovieDate[i]);
            movie.setDescription(dataMovieDescription[i]);
            movie.setRate(dataMovieRating[i]);
            movie.setRevenue(dataMovieRevenue[i]);
            movie.setPhoto(dataMoviePhoto.getResourceId(i, -1));
            movies.add(movie);
        }
        view.showListMovies(movies);

        view.hideLoading();
    }
}
