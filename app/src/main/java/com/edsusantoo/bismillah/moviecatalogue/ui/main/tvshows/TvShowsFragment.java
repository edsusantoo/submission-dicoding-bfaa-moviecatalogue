package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;
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
    @BindView(R.id.label_tv_nothing_movies)
    TextView tvNothingMovies;
    @BindView(R.id.img_broken)
    ImageView imgBroken;
    private Context context;
    private TvShowsViewModel tvShowsViewModel;

    private Observer<TvShowResponse> getTvShows = new Observer<TvShowResponse>() {
        @Override
        public void onChanged(@Nullable TvShowResponse tvShowResponse) {
            if (tvShowResponse != null) {
                if (tvShowResponse.getResults() != null && tvShowResponse.getResults().size() != 0) {
                    hideLoading();
                    showListTvShow(tvShowResponse.getResults());
                } else {
                    hideLoading();
                    onMovieEmpty();
                }
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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

        tvShowsViewModel = ViewModelProviders.of(this).get(TvShowsViewModel.class);

        if (tvShowsViewModel.getLanguage() != null && !tvShowsViewModel.getLanguage().isEmpty()) {
            showLoading();
            tvShowsViewModel.getTvShow(tvShowsViewModel.getLanguage());
            tvShowsViewModel.getDataTvShows().observe(this, getTvShows);
        } else {
            showLoading();
            tvShowsViewModel.getTvShow(tvShowsViewModel.getLanguage());
            tvShowsViewModel.getDataTvShows().observe(this, getTvShows);
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
    public void showListTvShow(List<ResultsItem> data) {
        TvShowsAdapter adapter = new TvShowsAdapter(getContext(), data);
        recyclerTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTvShows.setAdapter(adapter);
        recyclerTvShows.setHasFixedSize(true);
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
        if (tvShowsViewModel.getLanguage() != null && !tvShowsViewModel.getLanguage().isEmpty()) {
            tvShowsViewModel.getTvShow(tvShowsViewModel.getLanguage());
            tvShowsViewModel.getDataTvShows().observe(this, getTvShows);
        }
        hideLoading();
    }
}
