package com.edsusantoo.bismillah.moviecatalogue.ui.detailmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date_release)
    TextView tvDateRelease;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.label_revenue)
    TextView labelRevenue;
    @BindView(R.id.tv_revenue)
    TextView tvRevenue;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.img_movie)
    ImageView imgMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

            if (getDataIntent().getRevenue() == null) {
                labelRevenue.setVisibility(View.GONE);
                tvRevenue.setVisibility(View.GONE);
            }
        }
    }
}
