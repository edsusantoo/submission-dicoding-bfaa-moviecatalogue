package com.edsusantoo.bismillah.moviecatalogue.data;

import android.arch.lifecycle.LiveData;

import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.User;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import java.util.List;

import io.reactivex.Observable;

interface Repository {
    Observable<MovieResponse> getMovie(String api_key, String language);

    Observable<TvShowResponse> getTvShow(String api_key, String language);

    void insertUser(User user);

    void insertMovie(Movie movie);

    void insertFavorite(Favorites favorites);

    LiveData<List<Movie>> getAllMovie();

    LiveData<List<User>> getAllUser();

    LiveData<List<Favorites>> getAllFavorites();

    String getLanguage();

    void onDestroy();
}
