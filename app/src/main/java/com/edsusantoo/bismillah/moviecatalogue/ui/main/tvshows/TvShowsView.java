package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;

import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.ResultsItem;

import java.util.List;

interface TvShowsView {

    void showLoading();

    void hideLoading();

    void showListTvShow(List<ResultsItem> data);


    void onMovieEmpty();

    void onErrorConnection(String message);
}
