package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.movies.FavoriteMoviesFragment;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.tvshows.FavoriteTvShowsFragment;

public class FavoritesViewPagerAdapter extends FragmentStatePagerAdapter {
    public FavoritesViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavoriteMoviesFragment();
            case 1:
                return new FavoriteTvShowsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";
            case 1:
                return "Tv Shows";
        }
        return null;
    }

}