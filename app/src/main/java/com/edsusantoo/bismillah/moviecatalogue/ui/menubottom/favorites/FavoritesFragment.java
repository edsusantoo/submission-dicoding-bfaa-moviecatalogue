package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.ui.changelanguage.ChangeLanguageActivity;
import com.edsusantoo.bismillah.moviecatalogue.ui.menubottom.favorites.adapter.FavoritesViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.tab_favorites)
    TabLayout tabLayoutFavorites;
    @BindView(R.id.view_pager_favorites)
    ViewPager viewPageFavorites;
    @BindView(R.id.img_setting)
    ImageView imgSetting;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        setViewPagerMain();
        setTabLayoutMain();
        imgSetting.setOnClickListener(this);
        return view;
    }

    private void setViewPagerMain() {
        FavoritesViewPagerAdapter mainViewPagerAdapter = new FavoritesViewPagerAdapter(getChildFragmentManager());
        viewPageFavorites.setAdapter(mainViewPagerAdapter);
    }

    private void setTabLayoutMain() {
        tabLayoutFavorites.setupWithViewPager(viewPageFavorites);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_setting) {
            startActivity(new Intent(getActivity(), ChangeLanguageActivity.class));
        }
    }
}
