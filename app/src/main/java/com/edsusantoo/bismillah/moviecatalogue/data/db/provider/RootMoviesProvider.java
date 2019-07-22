package com.edsusantoo.bismillah.moviecatalogue.data.db.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.edsusantoo.bismillah.moviecatalogue.data.db.RootMoviesDB;
import com.edsusantoo.bismillah.moviecatalogue.data.db.dao.FavoriteDao;
import com.edsusantoo.bismillah.moviecatalogue.data.db.dao.MovieDao;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

public class RootMoviesProvider extends ContentProvider {
    public static final String AUTHORITY = "com.edsusantoo.bismillah.moviecatalogue";

    //content://com.edsusantoo.bismillah.moviecatalogue/favorite
    public static final Uri URI_FAVORITES = Uri.parse(
            "content://" + AUTHORITY + "/" + Constant.TABLE_NAME_FAVORITE);

    //content://com.edsusantoo.bismillah.moviecatalogue/movie
    public static final Uri URI_MOVIES = Uri.parse(
            "content://" + AUTHORITY + "/" + Constant.TABLE_NAME_MOVIE);

    private static final String SCHEME = "content";
    private static final int FAVORITE = 1;
    private static final int MOVIE = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, Constant.TABLE_NAME_FAVORITE, FAVORITE);
        uriMatcher.addURI(AUTHORITY, Constant.TABLE_NAME_MOVIE, MOVIE);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int code = uriMatcher.match(uri);
        final Cursor cursor;
        final Context context = getContext();
        switch (code) {
            case FAVORITE:
                if (context == null) {
                    cursor = null;
                } else {
                    FavoriteDao favorite = RootMoviesDB.getDatabase(context).favoriteDao();
                    cursor = favorite.getAllFavoriteContentProvider();
                }
                break;
            case MOVIE:
                if (context == null) {
                    cursor = null;
                } else {
                    MovieDao movie = RootMoviesDB.getDatabase(context).movieDao();
                    cursor = movie.getAllMovieContentProvider();
                }
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
