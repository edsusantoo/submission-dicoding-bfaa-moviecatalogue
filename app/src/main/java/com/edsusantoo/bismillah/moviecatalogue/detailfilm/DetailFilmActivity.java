package com.edsusantoo.bismillah.moviecatalogue.detailfilm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);


    }

    private void getDataIntent() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE_DETAIL);

    }
}
