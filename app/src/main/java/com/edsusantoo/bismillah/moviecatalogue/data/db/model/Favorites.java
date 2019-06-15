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

}
