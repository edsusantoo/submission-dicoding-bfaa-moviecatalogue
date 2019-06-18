package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.tvshows.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteTvShowsAdapter extends RecyclerView.Adapter<FavoriteTvShowsAdapter.FavoriteTvShowsViewHolder> {
    private ArrayList<Movie> dataTvShows = new ArrayList<>();
    private Context context;

    public FavoriteTvShowsAdapter(Context context) {
        this.context = context;
    }

    public void addMovie(List<Movie> movies) {
        dataTvShows.addAll(movies);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavoriteTvShowsAdapter.FavoriteTvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, viewGroup, false);
        return new FavoriteTvShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvShowsAdapter.FavoriteTvShowsViewHolder holder, int position) {
        Movie movie = dataTvShows.get(position);
        holder.tvTitle.setText(movie.getMovieName());
        holder.tvDescription.setText(movie.getDescription());
        Glide.with(context).load(movie.getMoviePhoto())
                .centerCrop()
                .placeholder(R.drawable.ic_image_grey_100dp)
                .error(R.drawable.ic_broken_image_grey_100dp)
                .into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        if (dataTvShows == null) return 0;
        return dataTvShows.size();
    }

    class FavoriteTvShowsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.img_movie)
        ImageView imgMovie;

        FavoriteTvShowsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
