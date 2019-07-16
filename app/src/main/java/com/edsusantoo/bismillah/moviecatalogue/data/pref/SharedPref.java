package com.edsusantoo.bismillah.moviecatalogue.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {
    private Context mContext;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        this.mContext = context;
        sharedPref = mContext.getSharedPreferences(Constant.PREF_NAME, MODE_PRIVATE);
    }

    public void modifyDataSharedPref(String name, String value) {
        editor = sharedPref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public void modifyDataSharedPrefBoolean(String name, Boolean value) {
        editor = sharedPref.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

}
