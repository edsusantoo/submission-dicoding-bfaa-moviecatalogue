package com.edsusantoo.bismillah.moviecatalogue.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.main.adapter.MainViewPagerAdapater;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayoutMain;
    private ViewPager viewPagerMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayoutMain = findViewById(R.id.tab_main);
        viewPagerMain = findViewById(R.id.view_pager_main);

        setViewPagerMain();
        setTabLayoutMain();


    }

    private void setViewPagerMain() {
        MainViewPagerAdapater mainViewPagerAdapater = new MainViewPagerAdapater(getSupportFragmentManager());
        viewPagerMain.setAdapter(mainViewPagerAdapater);
    }

    private void setTabLayoutMain() {
        tabLayoutMain.setupWithViewPager(viewPagerMain);
    }

}
