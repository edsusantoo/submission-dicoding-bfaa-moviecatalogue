package com.edsusantoo.bismillah.moviecatalogue.data.network;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("movie")
    Single<MovieResponse> getMovie(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("tv")
    Single<TvShowResponse> getTvMovie(
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}
