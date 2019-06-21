package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.FavoritesFragment;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.movies.MoviesFragment;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.tvshows.TvShowsFragment;

public class MenuBottomViewPager extends FragmentPagerAdapter {

    MenuBottomViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();
            case 1:
                return new TvShowsFragment();
            case 2:
                return new FavoritesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
