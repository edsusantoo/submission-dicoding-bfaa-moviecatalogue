package com.edsusantoo.bismillah.moviecatalogue.ui.detailmovie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.Movie;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

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

    private Favorites favorite;

    private DetailMovieViewModel detailMovieViewModel;
    private Observer<Favorites> getMovieFavorite = new Observer<Favorites>() {
        @Override
        public void onChanged(@Nullable Favorites favorites) {
            if (favorites != null) {
                favorite = favorites;
                if (favorites.getMovieId() == getDataIntent().getMovieId()) {
                    imgFavorite.setColorFilter(getResources().getColor(R.color.colorFavorite));
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


        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);

        detailMovieViewModel.getMovieFavorite().observe(this, getMovieFavorite);
        detailMovieViewModel.getMessageError().observe(this, getMessageError);
        detailMovieViewModel.getMessageSuccess().observe(this, getMessageSuccess);

        detailMovieViewModel.getMovieFavorite(getDataIntent().getMovieId());

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
        if (v.getId() == R.id.cv_favorite) {
            if (getDataIntent() != null) {
                switch (getDataIntent().getType()) {
                    case Constant.TYPE_MOVIE:
                        if (favorite != null && favorite.getMovieId() == getDataIntent().getMovieId()) {
                            imgFavorite.setColorFilter(getResources().getColor(R.color.colorPrimary));
                            detailMovieViewModel.deleteMovie(new com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie(
                                    getDataIntent().getMovieId(),
                                    getDataIntent().getTitle(),
                                    getDataIntent().getPhoto(),
                                    getDataIntent().getDescription(),
                                    Constant.TYPE_MOVIE
                            ));

                            detailMovieViewModel.deleteFavorite(new Favorites(
                                    favorite.getFavoritesId(),
                                    1,
                                    getDataIntent().getMovieId()
                            ));
                        } else {
                            imgFavorite.setColorFilter(getResources().getColor(R.color.colorFavorite));
                            detailMovieViewModel.insertMovie(new com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie(
                                    getDataIntent().getMovieId(),
                                    getDataIntent().getTitle(),
                                    getDataIntent().getPhoto(),
                                    getDataIntent().getDescription(),
                                    Constant.TYPE_MOVIE
                            ));
                            detailMovieViewModel.insertFavorite(new Favorites(
                                    1,
                                    getDataIntent().getMovieId()
                            ));
                        }
                        break;

                    case Constant.TYPE_TVSHOWS:
                        if (favorite != null && favorite.getMovieId() == getDataIntent().getMovieId()) {
                            imgFavorite.setColorFilter(getResources().getColor(R.color.colorPrimary));
                            detailMovieViewModel.deleteMovie(new com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie(
                                    getDataIntent().getMovieId(),
                                    getDataIntent().getTitle(),
                                    getDataIntent().getPhoto(),
                                    getDataIntent().getDescription(),
                                    Constant.TYPE_TVSHOWS
                            ));

                            detailMovieViewModel.deleteFavorite(new Favorites(
                                    favorite.getFavoritesId(),
                                    1,
                                    getDataIntent().getMovieId()
                            ));
                        } else {
                            imgFavorite.setColorFilter(getResources().getColor(R.color.colorFavorite));
                            detailMovieViewModel.insertMovie(new com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie(
                                    getDataIntent().getMovieId(),
                                    getDataIntent().getTitle(),
                                    getDataIntent().getPhoto(),
                                    getDataIntent().getDescription(),
                                    Constant.TYPE_TVSHOWS
                            ));
                            detailMovieViewModel.insertFavorite(new Favorites(
                                    1,
                                    getDataIntent().getMovieId()
                            ));
                        }
                        break;
                }
            }


        }
    }

    private void onMessageError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void onMessageSucces(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
