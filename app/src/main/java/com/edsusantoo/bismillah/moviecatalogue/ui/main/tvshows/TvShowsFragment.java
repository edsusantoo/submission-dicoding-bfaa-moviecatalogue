package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;


import android.annotation.SuppressLint;
import android.content.res.TypedArray;
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
import com.edsusantoo.bismillah.moviecatalogue.data.Movie;
import com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows.adapter.TvShowsAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements TvShowsView, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerTvShows;
    private SwipeRefreshLayout swipeRefresh;
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

        recyclerTvShows = view.findViewById(R.id.recycler_tvmovie);
        swipeRefresh = view.findViewById(R.id.swipe);

        swipeRefresh.setOnRefreshListener(this);

        prepared();

    }

    private void prepared() {
        String[] dataMovieName = getResources().getStringArray(R.array.data_movie_name);
        String[] dataMovieDate = getResources().getStringArray(R.array.data_movie_date);
        String[] dataMovieDescription = getResources().getStringArray(R.array.data_movie_description);
        String[] dataRating = getResources().getStringArray(R.array.data_movie_rating);
        String[] dataRevenue = getResources().getStringArray(R.array.data_movie_revenue);
        @SuppressLint("Recycle") TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_movie_photo);

        presenter.addMovie(dataMovieName, dataMovieDate, dataMovieDescription, dataRating, dataRevenue, dataPhoto);
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
    public void showListMovies(List<Movie> movies) {
        TvShowsAdapter adapter = new TvShowsAdapter(getContext(), movies);
        recyclerTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTvShows.setAdapter(adapter);
        recyclerTvShows.setHasFixedSize(true);
    }

    @Override
    public void onRefresh() {
        prepared();
    }
}
