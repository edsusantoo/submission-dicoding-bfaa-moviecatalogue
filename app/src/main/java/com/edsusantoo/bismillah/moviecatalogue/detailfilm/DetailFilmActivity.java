package com.edsusantoo.bismillah.moviecatalogue.detailfilm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";

    private TextView tvTitle, tvDateRelease, tvRating, tvRevenue, tvDescription;
    private ImageView imgMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        tvTitle = findViewById(R.id.tv_title);
        tvDateRelease = findViewById(R.id.tv_date_release);
        tvRating = findViewById(R.id.tv_rating);
        tvRevenue = findViewById(R.id.tv_revenue);
        tvDescription = findViewById(R.id.tv_description);
        imgMovie = findViewById(R.id.img_movie);

        getDataIntent();

        setDataIntent();


    }

    private Movie getDataIntent() {
        return getIntent().getParcelableExtra(EXTRA_MOVIE_DETAIL);
    }

    private void setDataIntent() {
        if (getDataIntent() != null) {
            tvTitle.setText(getDataIntent().getTitle());
            tvDateRelease.setText(getDataIntent().getDate());
            tvRating.setText(getDataIntent().getRate());
            tvRevenue.setText(getDataIntent().getRevenue());
            tvDescription.setText(getDataIntent().getDescription());
            imgMovie.setImageResource(getDataIntent().getPhoto());
        }
    }
}
