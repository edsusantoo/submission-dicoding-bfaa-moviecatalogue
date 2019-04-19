package com.edsusantoo.bismillah.moviecatalogue.daftarfilm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.model.Movie;
import com.edsusantoo.bismillah.moviecatalogue.detailfilm.DetailFilmActivity;

import java.util.ArrayList;

public class DaftarFilmAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;

    public DaftarFilmAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);

        viewHolder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailFilmActivity.class);
                context.startActivity(i);
            }
        });

        return view;
    }

    private class ViewHolder {
        private TextView tvTittle, tvDate, tvDescription;
        private ImageView imgMovie;
        private CardView cvMovie;

        ViewHolder(View view) {
            tvTittle = view.findViewById(R.id.tv_title);
            tvDate = view.findViewById(R.id.tv_date);
            tvDescription = view.findViewById(R.id.tv_description);
            imgMovie = view.findViewById(R.id.img_movie);
            cvMovie = view.findViewById(R.id.cv_movie);
        }

        void bind(Movie movie) {
            tvTittle.setText(movie.getTitle());
            tvDate.setText(movie.getDate());
            tvDescription.setText(movie.getDescription());
            imgMovie.setImageResource(movie.getPhoto());
        }
    }
}
