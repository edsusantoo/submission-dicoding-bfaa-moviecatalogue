package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.adapter.FavoritesViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment {
    @BindView(R.id.tab_favorites)
    TabLayout tabLayoutFavorites;
    @BindView(R.id.view_pager_favorites)
    ViewPager viewPageFavorites;
    @BindView(R.id.img_setting)
    ImageView imgSetting;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setViewPagerMain();
        setTabLayoutMain();
    }

    private void setViewPagerMain() {
        FavoritesViewPagerAdapter mainViewPagerAdapter = new FavoritesViewPagerAdapter(getChildFragmentManager());
        viewPageFavorites.setAdapter(mainViewPagerAdapter);
    }

    private void setTabLayoutMain() {
        tabLayoutFavorites.setupWithViewPager(viewPageFavorites);
    }
}
