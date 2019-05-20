package com.edsusantoo.bismillah.moviecatalogue.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.ui.changelanguage.ChangeLanguageActivity;
import com.edsusantoo.bismillah.moviecatalogue.ui.main.adapter.MainViewPagerAdapter;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQUEST_CODE_CHANGE_LANGUAGE = 101;
    @BindView(R.id.tab_main)
    TabLayout tabLayoutMain;
    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;
    @BindView(R.id.img_setting)
    ImageView imgSetting;
    private String language = null;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setViewPagerMain();
        setTabLayoutMain();

        imgSetting.setOnClickListener(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if (mainViewModel.getLanguage() != null && !mainViewModel.getLanguage().isEmpty()) {
            setLanguage(mainViewModel.getLanguage());
        }
    }

    private void setViewPagerMain() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPagerMain.setAdapter(mainViewPagerAdapter);
    }

    private void setTabLayoutMain() {
        tabLayoutMain.setupWithViewPager(viewPagerMain);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_setting) {
            Intent i = new Intent(MainActivity.this, ChangeLanguageActivity.class);
            i.putExtra(ChangeLanguageActivity.EXTRA_LANGUAGE, language);
            startActivityForResult(i, REQUEST_CODE_CHANGE_LANGUAGE);
        }
    }

    private void setLanguage() {
        SharedPref sharedPref = new SharedPref(this);
        Locale locale = new Locale(sharedPref.getSharedPref().getString(Constant.PREF_LANGUAGE, ""));
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    private void saveLanguage(String language) {
        SharedPref sharedPref = new SharedPref(this);
        sharedPref.modifyDataSharedPref(Constant.PREF_LANGUAGE, language);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHANGE_LANGUAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    language = data.getStringExtra(ChangeLanguageActivity.EXTRA_LANGUAGE);
                    if (language != null) {
                        saveLanguage(language);
                        setLanguage();
                        //untuk refresh
                        setTabLayoutMain();
                        setViewPagerMain();
                    }
                }
            }
        }
    }

    private void setLanguage(String language) {
        saveLanguage(language);
        setLanguage();
    }
}
