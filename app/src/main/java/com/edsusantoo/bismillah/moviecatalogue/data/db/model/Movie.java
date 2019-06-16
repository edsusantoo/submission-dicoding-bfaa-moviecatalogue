package com.edsusantoo.bismillah.moviecatalogue.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int movieId;

    @ColumnInfo(name = "movie_name")
    private String movieName;

    @ColumnInfo(name = "movie_photo")
    private String moviePhoto;

    @ColumnInfo(name = "description")
    private String description;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePhoto() {
        return moviePhoto;
    }

    public void setMoviePhoto(String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
