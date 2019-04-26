package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;


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
import com.edsusantoo.bismillah.moviecatalogue.ui.main.movies.adapter.ListMoviesAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MoviesView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerListMovie;
    private SwipeRefreshLayout swipeRefresh;
    private MoviesPresenter presenter;

    public MoviesFragment() {
        presenter = new MoviesPresenter(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerListMovie = view.findViewById(R.id.recycler_list_movie);
        swipeRefresh = view.findViewById(R.id.swipe);

        swipeRefresh.setOnRefreshListener(this);

        prepared();

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
        ListMoviesAdapter adapter = new ListMoviesAdapter(getContext(), movies);
        LinearLayoutManager llManager = new LinearLayoutManager(getActivity());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerListMovie.setLayoutManager(llManager);
        recyclerListMovie.setAdapter(adapter);
        recyclerListMovie.setHasFixedSize(true);
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
    public void onRefresh() {
        prepared();
    }
}
