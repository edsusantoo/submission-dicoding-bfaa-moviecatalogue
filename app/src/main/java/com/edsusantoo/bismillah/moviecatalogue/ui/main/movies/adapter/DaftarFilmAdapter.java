package com.edsusantoo.bismillah.moviecatalogue.ui.main.movies.adapter;

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

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.Movie;
import com.edsusantoo.bismillah.moviecatalogue.ui.detailmovie.DetailMovieActivity;

import java.util.List;

public class DaftarFilmAdapter extends RecyclerView.Adapter<DaftarFilmAdapter.DaftarFilmViewHolder> {

    private Context context;
    private List<Movie> movies;

    public DaftarFilmAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public DaftarFilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_movie, viewGroup, false);
        return new DaftarFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarFilmViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        holder.tvTittle.setText(movie.getTitle());
        holder.tvDateRelease.setText(movie.getDate());
        holder.tvDescription.setText(movie.getDescription());
        holder.imgMovie.setImageResource(movie.getPhoto());

        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailMovieActivity.class);
                Movie dataMovie = new Movie();
                dataMovie.setTitle(movie.getTitle());
                dataMovie.setDate(movie.getDate());
                dataMovie.setDescription(movie.getDescription());
                dataMovie.setRevenue(movie.getRevenue());
                dataMovie.setRate(movie.getRate());
                dataMovie.setPhoto(movie.getPhoto());
                i.putExtra(DetailMovieActivity.EXTRA_MOVIE_DETAIL, dataMovie);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    class DaftarFilmViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTittle, tvDateRelease, tvDescription;
        private ImageView imgMovie;
        private CardView cvMovie;

        DaftarFilmViewHolder(View view) {
            super(view);
            tvTittle = view.findViewById(R.id.tv_title);
            tvDateRelease = view.findViewById(R.id.tv_date_release);
            tvDescription = view.findViewById(R.id.tv_description);
            imgMovie = view.findViewById(R.id.img_movie);
            cvMovie = view.findViewById(R.id.cv_list_movie);
        }

    }
}
