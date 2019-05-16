package com.edsusantoo.bismillah.moviecatalogue.data;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

import io.reactivex.Observable;

interface Repository {
    Observable<MovieResponse> getMovie(String api_key, String language);

    Observable<TvShowResponse> getTvShow(String api_key, String language);

    String getLanguage();

    void onDestroy();
}
