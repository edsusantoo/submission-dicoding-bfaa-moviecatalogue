package com.edsusantoo.bismillah.moviecatalogue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.edsusantoo.bismillah.moviecatalogue.daftarfilm.DaftarFilmFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDaftarFilmFragment();

    }

    private void showDaftarFilmFragment() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        DaftarFilmFragment mDaftarFilmFragment = new DaftarFilmFragment();

        Fragment fragment = mFragmentManager.findFragmentByTag(DaftarFilmFragment.class.getSimpleName());
        if (!(fragment instanceof DaftarFilmFragment)) {
            mFragmentTransaction.add(R.id.frame_container, mDaftarFilmFragment, DaftarFilmFragment.class.getSimpleName());
            mFragmentTransaction.commit();
        }
    }
}
