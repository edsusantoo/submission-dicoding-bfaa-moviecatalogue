package com.edsusantoo.bismillah.moviecatalogue.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    List<Favorites> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favorites... favorites);

    @Delete
    void delete(Favorites... favorites);

    @Query("SELECT * FROM favorites WHERE id =:id")
    List<Favorites> getMovie(int id);
}
