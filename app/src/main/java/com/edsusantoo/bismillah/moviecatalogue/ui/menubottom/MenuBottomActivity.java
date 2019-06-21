package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.FavoritesFragment;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.movies.MoviesFragment;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.tvshows.TvShowsFragment;
import com.edsusantoo.bismillah.moviecatalogue.utils.NonSwappableViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

//TODO:muncul errror ketika tidak ada koneksi
public class MenuBottomActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private static final String TAG_MOVIES = "tagmovies";
    private static final String TAG_TVSHOWS = "tagtvshows";
    private static final String TAG_FAVORITES = "tagfavorites";
    @BindView(R.id.menu_bottom_navigation)
    AHBottomNavigation menuBottom;
    @BindView(R.id.view_pager)
    NonSwappableViewPager viewPager;
    private MoviesFragment moviesFragment;
    private TvShowsFragment tvShowsFragment;
    private FavoritesFragment favoritesFragment;

    private MenuBottomViewPager adapterViewPager;


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
//        moviesFragment = new MoviesFragment();
//        callFragment(moviesFragment, TAG_MOVIES);

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


        adapterViewPager = new MenuBottomViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.addOnPageChangeListener(this);

    }

    private int fetchColor(int color) {
        return ContextCompat.getColor(this, color);
    }

//    public void callFragment(Fragment fragment, String tag) {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame_main, fragment, tag).commit();
//    }

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
            case TAG_FAVORITES:
                favoritesFragment = (FavoritesFragment)
                        getSupportFragmentManager().findFragmentByTag(TAG_FAVORITES);
                break;
        }
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        switch (position) {
            case 0:
                if (menuBottom.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0);
                    adapterViewPager.notifyDataSetChanged();
                }
                break;
            case 1:
                if (menuBottom.getCurrentItem() != 1) {
                    viewPager.setCurrentItem(1);
                    adapterViewPager.notifyDataSetChanged();
                }
                break;
            case 2:
                if (menuBottom.getCurrentItem() != 2) {
                    viewPager.setCurrentItem(2);
                    adapterViewPager.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
