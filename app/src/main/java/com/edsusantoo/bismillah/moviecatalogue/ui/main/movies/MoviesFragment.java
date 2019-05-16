package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.ui.main.movies.adapter.ListMoviesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MoviesView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_list_movie)
    RecyclerView recyclerListMovie;
    @BindView(R.id.label_tv_nothing_movies)
    TextView tvNothingMovies;
    @BindView(R.id.img_broken)
    ImageView imgBroken;

    private MoviesViewModel moviesViewModel;

    private Observer<MovieResponse> getMovies = new Observer<MovieResponse>() {
        @Override
        public void onChanged(@Nullable MovieResponse movieResponse) {
            if (movieResponse != null) {
                if (movieResponse.getResults() != null && movieResponse.getResults().size() != 0) {
                    hideLoading();
                    showListMovies(movieResponse.getResults());
                } else {
                    hideLoading();
                    onMovieEmpty();
                }
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        swipeRefresh.setOnRefreshListener(this);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        if (moviesViewModel.getLanguage() != null && !moviesViewModel.getLanguage().isEmpty()) {
            showLoading();
            moviesViewModel.getMovies(moviesViewModel.getLanguage());
            moviesViewModel.getDataMovies().observe(this, getMovies);
        } else {
            showLoading();
            moviesViewModel.getMovies(moviesViewModel.getLanguage());
            moviesViewModel.getDataMovies().observe(this, getMovies);
        }

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
    public void showListMovies(List<ResultsItem> movies) {
        ListMoviesAdapter adapter = new ListMoviesAdapter(getContext(), movies);
        LinearLayoutManager llManager = new LinearLayoutManager(getActivity());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerListMovie.setLayoutManager(llManager);
        recyclerListMovie.setAdapter(adapter);
        recyclerListMovie.setHasFixedSize(true);
    }

    @Override
    public void onMovieEmpty() {
        tvNothingMovies.setVisibility(View.VISIBLE);
        imgBroken.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorConnection(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        if (moviesViewModel.getLanguage() != null && !moviesViewModel.getLanguage().isEmpty()) {
            moviesViewModel.getMovies(moviesViewModel.getLanguage());
            moviesViewModel.getDataMovies().observe(this, getMovies);
        }
        hideLoading();
    }

}
