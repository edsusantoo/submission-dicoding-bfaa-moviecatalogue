package com.edsusantoo.bismillah.moviecatalogue.data;

import android.arch.lifecycle.LiveData;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;

interface Repository {
    LiveData<MovieResponse> getMovie(String api_key, String language);

    String getLanguage();
}
