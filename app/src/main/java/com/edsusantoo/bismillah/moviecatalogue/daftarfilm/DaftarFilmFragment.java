package com.edsusantoo.bismillah.moviecatalogue.daftarfilm;


import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.adapter.DaftarFilmAdapter;
import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarFilmFragment extends Fragment implements DaftarFilmView, SwipeRefreshLayout.OnRefreshListener {

    private ListView lvMovie;
    private SwipeRefreshLayout swipeRefresh;
    private DaftarFilmPresenter presenter;

    public DaftarFilmFragment() {
        presenter = new DaftarFilmPresenter(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daftar_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvMovie = view.findViewById(R.id.lv_movie);
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
    public void showDaftarFilm(ArrayList<Movie> movies) {

        DaftarFilmAdapter adapter = new DaftarFilmAdapter(getContext(), movies);
        lvMovie.setDivider(null);
        lvMovie.setAdapter(adapter);
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
