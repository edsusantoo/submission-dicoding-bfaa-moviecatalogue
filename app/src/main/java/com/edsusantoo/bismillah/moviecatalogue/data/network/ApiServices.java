package com.edsusantoo.bismillah.moviecatalogue.data.network;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("discover/movie")
    Single<MovieResponse> getMovie(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("discover/tv")
    Single<TvShowResponse> getTvMovie(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("search/movie")
    Observable<MovieResponse> getSearchMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("search/tv")
    Observable<TvShowResponse> getSearchTvShow(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );
}
