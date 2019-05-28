package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.edsusantoo.bismillah.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuBottomActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    @BindView(R.id.menu_bottom_navigation)
    AHBottomNavigation menuBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom);
        ButterKnife.bind(this);

        createNavItems();

    }

    void createNavItems() {
        AHBottomNavigationItem movies = new AHBottomNavigationItem("Movies", AppCompatResources.getDrawable(this, R.drawable.ic_movie));
        AHBottomNavigationItem tvshows = new AHBottomNavigationItem("Tv Shows", AppCompatResources.getDrawable(this, R.drawable.ic_tv));
        AHBottomNavigationItem favorite = new AHBottomNavigationItem("Favorite", AppCompatResources.getDrawable(this, R.drawable.ic_favorite));

        menuBottom.addItem(movies);
        menuBottom.addItem(tvshows);
        menuBottom.addItem(favorite);

        menuBottom.setDefaultBackgroundColor(fetchColor(R.color.colorPrimary));
        menuBottom.setAccentColor(fetchColor(R.color.colorSelectedTab));
        menuBottom.setInactiveColor(fetchColor(R.color.white));
        menuBottom.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        menuBottom.setForceTint(false);
        menuBottom.setOnTabSelectedListener(this);

    }

    public int fetchColor(int color) {
        return ContextCompat.getColor(this, color);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        return false;
    }
}
