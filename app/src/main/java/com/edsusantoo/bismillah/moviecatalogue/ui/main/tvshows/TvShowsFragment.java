package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows.adapter.TvShowsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements TvShowsView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_tvmovie)
    RecyclerView recyclerTvShows;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefresh;
    private TvShowsPresenter presenter;

    public TvShowsFragment() {

        presenter = new TvShowsPresenter(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        swipeRefresh.setOnRefreshListener(this);

        presenter.getTvMovie();

    }


    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showListTvShow(List<ResultsItem> data) {
        TvShowsAdapter adapter = new TvShowsAdapter(getContext(), data);
        recyclerTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTvShows.setAdapter(adapter);
        recyclerTvShows.setHasFixedSize(true);
    }

    @Override
    public void onMovieEmpty() {

    }

    @Override
    public void onErrorConnection(String massage) {

    }


    @Override
    public void onRefresh() {
        presenter.getTvMovie();
    }
}
