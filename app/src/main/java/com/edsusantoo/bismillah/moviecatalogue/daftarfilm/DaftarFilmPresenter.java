package com.edsusantoo.bismillah.moviecatalogue.daftarfilm;

import android.content.res.TypedArray;

import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;

import java.util.ArrayList;

class DaftarFilmPresenter {
    private DaftarFilmView view;

    DaftarFilmPresenter(DaftarFilmView view) {
        this.view = view;
    }

    void addMovie(String[] dataMovieName,
                  String[] dataMovieDate,
                  String[] dataMovieDescription,
                  String[] dataMovieRating,
                  String[] dataMovieRevenue,
                  TypedArray dataMoviePhoto) {

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
        view.showDaftarFilm(movies);
    }
}
