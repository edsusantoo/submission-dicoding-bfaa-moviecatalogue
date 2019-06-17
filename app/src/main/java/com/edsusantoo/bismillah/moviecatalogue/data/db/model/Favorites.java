package com.edsusantoo.bismillah.moviecatalogue.data.db.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorites {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int favoritesId;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    public Favorites(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
