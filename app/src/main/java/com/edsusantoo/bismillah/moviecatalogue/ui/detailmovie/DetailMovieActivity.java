package com.edsusantoo.bismillah.moviecatalogue.ui.detailmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.img_movie)
    ImageView imgMovie;
    @BindView(R.id.cv_favorite)
    CardView cvFavorite;
    @BindView(R.id.img_favorite)
    ImageView imgFavorite;

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

        cvFavorite.setOnClickListener(this);


    }

    private Movie getDataIntent() {
        return getIntent().getParcelableExtra(EXTRA_MOVIE_DETAIL);
    }

    private void setDataIntent() {
        if (getDataIntent() != null) {
            tvTitle.setText(getDataIntent().getTitle());
            tvDescription.setText(getDataIntent().getDescription());
            Glide.with(this).load(getDataIntent().getPhoto())
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_grey_100dp)
                    .error(R.drawable.ic_broken_image_grey_100dp)
                    .into(imgMovie);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_favorite:
                imgFavorite.setColorFilter(getResources().getColor(R.color.colorFavorite));
                break;
        }
    }
}
