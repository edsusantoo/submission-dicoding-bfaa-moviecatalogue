package com.edsusantoo.bismillah.moviecatalogue.ui.changelanguage;

import android.content.Context;

import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

class ChangeLanguagePresenter {
    private ChangeLanguageView view;

    ChangeLanguagePresenter(ChangeLanguageView view) {
        this.view = view;
    }


    String getLanguage(Context context) {
        SharedPref sharedPref = new SharedPref(context);
        return sharedPref.getSharedPref().getString(Constant.PREF_LANGUAGE, "");
    }
}
