package com.edsusantoo.bismillah.moviecatalogue.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Favorites;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.Movie;
import com.edsusantoo.bismillah.moviecatalogue.data.db.model.User;

@Database(entities = {User.class, Favorites.class, Movie.class}, version = 1)
public abstract class RootMoviesDB extends RoomDatabase {
    private static RootMoviesDB INSTANCE;

    static RootMoviesDB getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (RootMoviesDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RootMoviesDB.class,
                            "rootmovie.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract RootMoviesDB rootMoviesDB();
}
