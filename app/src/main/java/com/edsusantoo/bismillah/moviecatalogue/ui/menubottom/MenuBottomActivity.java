package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.movies.MoviesFragment;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.tvshows.TvShowsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuBottomActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private static final String TAG_MOVIES = "tagmovies";
    private static final String TAG_TVSHOWS = "tagtvshows";
    @BindView(R.id.menu_bottom_navigation)
    AHBottomNavigation menuBottom;
    private MoviesFragment moviesFragment;
    private TvShowsFragment tvShowsFragment;


    private MenuBottomViewModel menuBottomViewModel;

    private Observer<String> getPositionMenuBottom = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String TAG) {
            if (TAG != null) {
                selectFragmentState(TAG);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom);
        ButterKnife.bind(this);


        menuBottomViewModel = ViewModelProviders.of(this).get(MenuBottomViewModel.class);
        menuBottomViewModel.getPositionMenuBottom().observe(this, getPositionMenuBottom);

        //setfirst
        moviesFragment = new MoviesFragment();
        callFragment(moviesFragment, TAG_MOVIES);

        createNavItems();

    }

    void createNavItems() {
        AHBottomNavigationItem movies = new AHBottomNavigationItem("Movies", AppCompatResources.getDrawable(this, R.drawable.ic_movie));
        AHBottomNavigationItem tvShows = new AHBottomNavigationItem("Tv Shows", AppCompatResources.getDrawable(this, R.drawable.ic_tv));
        AHBottomNavigationItem favorite = new AHBottomNavigationItem("Favorite", AppCompatResources.getDrawable(this, R.drawable.ic_favorite));

        menuBottom.addItem(movies);
        menuBottom.addItem(tvShows);
        menuBottom.addItem(favorite);

        menuBottom.setDefaultBackgroundColor(fetchColor(R.color.colorPrimary));
        menuBottom.setAccentColor(fetchColor(R.color.colorSelectedTab));
        menuBottom.setInactiveColor(fetchColor(R.color.white));
        menuBottom.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        menuBottom.setForceTint(false);
        menuBottom.setOnTabSelectedListener(this);

    }

    private int fetchColor(int color) {
        return ContextCompat.getColor(this, color);
    }

    public void callFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main, fragment, tag).commit();
    }

    private void selectFragmentState(String lastTagSelected) {
        switch (lastTagSelected) {
            case TAG_MOVIES:
                moviesFragment = (MoviesFragment)
                        getSupportFragmentManager().findFragmentByTag(TAG_MOVIES);
                menuBottom.setCurrentItem(0);
                break;
            case TAG_TVSHOWS:
                tvShowsFragment = (TvShowsFragment)
                        getSupportFragmentManager().findFragmentByTag(TAG_TVSHOWS);
                menuBottom.setCurrentItem(1);
                break;
        }
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        switch (position) {
            case 0:
                if (menuBottom.getCurrentItem() != 0) {
                    menuBottomViewModel.setPositionMenuBottom(TAG_MOVIES);
                    moviesFragment = new MoviesFragment();
                    callFragment(moviesFragment, TAG_MOVIES);
                }
                break;
            case 1:
                if (menuBottom.getCurrentItem() != 1) {
                    menuBottomViewModel.setPositionMenuBottom(TAG_TVSHOWS);
                    tvShowsFragment = new TvShowsFragment();
                    callFragment(tvShowsFragment, TAG_TVSHOWS);
                }
                break;
            case 2:
                break;
            default:
                break;
        }
        return true;
    }
}
