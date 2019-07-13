package com.edsusantoo.bismillah.moviecatalogue.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    Maybe<List<Favorites>> getAll();

    @Query("SELECT * FROM favorites")
    List<Favorites> getAllWidget();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favorites... favorites);

    @Delete
    void delete(Favorites... favorites);

    @Query("SELECT * FROM favorites WHERE movie_id =:movieId")
    Maybe<Favorites> getMovieFavorite(int movieId);
}
