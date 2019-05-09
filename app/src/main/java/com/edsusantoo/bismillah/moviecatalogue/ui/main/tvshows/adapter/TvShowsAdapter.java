package com.edsusantoo.bismillah.moviecatalogue.ui.main.tvshows.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.Movie;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.ui.detailmovie.DetailMovieActivity;

import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder> {

    private Context context;
    private List<ResultsItem> tv_shows;

    public TvShowsAdapter(Context context, List<ResultsItem> tv_shows) {
        this.context = context;
        this.tv_shows = tv_shows;
    }

    @NonNull
    @Override
    public TvShowsAdapter.TvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tvmovie, viewGroup, false);
        return new TvShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsAdapter.TvShowsViewHolder holder, int position) {
        final ResultsItem tv_show = tv_shows.get(position);
        final String image_url = "https://image.tmdb.org/t/p/w500" + tv_show.getBackdropPath();


        holder.tvTittle.setText(tv_show.getOriginalName());
        holder.tvDescription.setText(tv_show.getOverview());
        Glide.with(context).load(image_url)
                .centerCrop()
                .placeholder(R.drawable.ic_image_grey_100dp)
                .error(R.drawable.ic_broken_image_grey_100dp)
                .into(holder.imgMovie);

        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailMovieActivity.class);
                Movie dataMovie = new Movie();
                dataMovie.setTitle(tv_show.getName());
                dataMovie.setDescription(tv_show.getOverview());
                dataMovie.setRate(tv_show.getVoteAverage());
                dataMovie.setPhoto(image_url);
                i.putExtra(DetailMovieActivity.EXTRA_MOVIE_DETAIL, dataMovie);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tv_shows.size();
    }

    class TvShowsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTittle, tvDateRelease, tvDescription;
        private ImageView imgMovie;
        private CardView cvMovie;

        TvShowsViewHolder(View view) {
            super(view);
            tvTittle = view.findViewById(R.id.tv_title);
            tvDateRelease = view.findViewById(R.id.tv_date_release);
            tvDescription = view.findViewById(R.id.tv_description);
            imgMovie = view.findViewById(R.id.img_tvmovie);
            cvMovie = view.findViewById(R.id.cv_tvmovie);
        }
    }
}
