package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.movies.adapter.ListMoviesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_list_movie)
    RecyclerView recyclerListMovie;
    @BindView(R.id.label_tv_nothing_movies)
    TextView tvNothingMovies;
    @BindView(R.id.img_broken)
    ImageView imgBroken;
    @BindView(R.id.search_movie)
    SearchView searchMovies;

    private MoviesViewModel moviesViewModel;
    private Observer<Boolean> getIsLoading = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if (isLoading != null) {
                if (isLoading) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        }
    };
    private Observer<String> getMessageError = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            if (s != null && !s.isEmpty()) {
                onMessageError(s);
            }
        }
    };
    private Observer<String> getMessageSuccess = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            onMessageSucces(s);
        }
    };
    private Observer<MovieResponse> getMovies = new Observer<MovieResponse>() {
        @Override
        public void onChanged(@Nullable MovieResponse movieResponse) {
            if (movieResponse != null) {
                if (movieResponse.getResults() != null && movieResponse.getResults().size() != 0) {
                    showListMovies(movieResponse.getResults());
                } else {
                    onMovieEmpty();
                }
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        swipeRefresh.setOnRefreshListener(this);
        searchMovies.setOnQueryTextListener(this);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getDataMovies().observe(this, getMovies);
        moviesViewModel.getIsLoading().observe(this, getIsLoading);
        moviesViewModel.getMessageError().observe(this, getMessageError);
        moviesViewModel.getMessageSuccess().observe(this, getMessageSuccess);

        if (moviesViewModel.getLanguage() != null && !moviesViewModel.getLanguage().isEmpty()) {
            moviesViewModel.getMovies(moviesViewModel.getLanguage());
        } else {
            moviesViewModel.getMovies(moviesViewModel.getLanguage());
        }

    }

    private void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    private void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    private void showListMovies(List<ResultsItem> movies) {
        ListMoviesAdapter adapter = new ListMoviesAdapter(getContext(), movies);
        LinearLayoutManager llManager = new LinearLayoutManager(getActivity());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerListMovie.setLayoutManager(llManager);
        recyclerListMovie.setAdapter(adapter);
        recyclerListMovie.setHasFixedSize(true);
    }

    public void onMovieEmpty() {
        tvNothingMovies.setVisibility(View.VISIBLE);
        imgBroken.setVisibility(View.VISIBLE);
    }

    private void onErrorConnection(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void onMessageError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void onMessageSucces(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        moviesViewModel.refreshMovies(moviesViewModel.getLanguage());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        moviesViewModel.getSearchMovies(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        moviesViewModel.getSearchMovies(s);
        return false;
    }
}
