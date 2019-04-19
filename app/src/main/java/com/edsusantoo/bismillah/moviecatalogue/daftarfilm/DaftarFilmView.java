package com.edsusantoo.bismillah.moviecatalogue.daftarfilm;

import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;

import java.util.ArrayList;

public interface DaftarFilmView {
    void showLoading();

    void hideLoading();

    void showDaftarFilm(ArrayList<Movie> movies);

}
