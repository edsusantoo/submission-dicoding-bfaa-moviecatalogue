package com.edsusantoo.bismillah.moviecatalogue.data;

import android.arch.lifecycle.LiveData;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;

interface Repository {
    LiveData<MovieResponse> getMovie(String api_key, String language);

    LiveData<TvShowResponse> getTvShow(String api_key, String language);

    String getLanguage();

    void onDestroy();
}
