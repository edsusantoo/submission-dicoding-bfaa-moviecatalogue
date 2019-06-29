package com.edsusantoo.bismillah.moviecatalogue.data;

import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.User;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

interface Repository {

    //======Response Network======
    Single<MovieResponse> getMovie(String api_key, String language);

    Single<TvShowResponse> getTvShow(String api_key, String language);

    Observable<MovieResponse> getSearchMovie(String api_key, String language, String query);

    Observable<TvShowResponse> getSearchTvShow(String api_key, String language, String query);

    //======MovieDao======
    void insertUser(User user);

    void insertMovie(Movie movie);

    void insertFavorite(Favorites favorites);

    void deleteMovie(Movie movie);

    void deleteFavorite(Favorites favorites);

    Maybe<List<Favorites>> getAllFavorite();

    Maybe<List<Movie>> getMovie(int movieId, String type);

    Maybe<Favorites> getMovieFavorites(int movieId);

    String getLanguage();

    void onDestroy();
}
