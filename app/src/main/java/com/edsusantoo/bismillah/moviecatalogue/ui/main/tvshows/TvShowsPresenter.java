package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import android.content.res.TypedArray;

import com.edsusantoo.bismillah.moviecatalogue.data.Movie;

import java.util.ArrayList;

class TvShowsPresenter {
    private TvShowsView view;

    TvShowsPresenter(TvShowsView view) {
        this.view = view;
    }

    void addMovie(String[] dataMovieName,
                  String[] dataMovieDate,
                  String[] dataMovieDescription,
                  String[] dataMovieRating,
                  TypedArray dataMoviePhoto) {
        view.showLoading();

        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < dataMovieName.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(dataMovieName[i]);
            movie.setDate(dataMovieDate[i]);
            movie.setDescription(dataMovieDescription[i]);
            movie.setRate(dataMovieRating[i]);
            movie.setPhoto(dataMoviePhoto.getResourceId(i, -1));
            movies.add(movie);
        }
        view.showListMovies(movies);

        view.hideLoading();
    }
}
