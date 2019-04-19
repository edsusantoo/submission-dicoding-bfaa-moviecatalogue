package com.edsusantoo.bismillah.moviecatalogue.daftarfilm;

import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;

import java.util.ArrayList;

public interface DaftarFilmView {
    void showShammer();

    void hideShammer();

    void showDaftarFilm(ArrayList<Movie> movies);

}
