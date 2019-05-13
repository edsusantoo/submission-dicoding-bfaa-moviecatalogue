package com.edsusantoo.bismillah.moviecatalogue.ui.main;

import android.content.Context;

import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

class MainPresenter {
    private MainView view;

    MainPresenter(MainView view) {
        this.view = view;
    }

    String getLanguage(Context context) {
        SharedPref sharedPref = new SharedPref(context);
        return sharedPref.getSharedPref().getString(Constant.PREF_LANGUAGE, "");
    }
}
