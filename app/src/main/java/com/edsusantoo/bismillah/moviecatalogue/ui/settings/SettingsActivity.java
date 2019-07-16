package com.edsusantoo.bismillah.moviecatalogue.ui.settings;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.edsusantoo.bismillah.moviecatalogue.R;
import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {
    public static final String EXTRA_LANGUAGE = "extra_language";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rg_change_language)
    RadioGroup rgChangeLanguage;
    @BindView(R.id.rb_indonesia)
    RadioButton rbIndonesia;
    @BindView(R.id.rb_english)
    RadioButton rbEnglish;
    private String language = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishChangeLanguage();
                finish();
            }
        });

        SettingsViewModel settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

        if (settingsViewModel.getLanguage() != null || settingsViewModel.getLanguage().isEmpty()) {
            setRadioButtonLanguage(settingsViewModel.getLanguage());
        }

        //for set radiobutton intent data
        language = getIntent().getStringExtra(EXTRA_LANGUAGE);
        if (language != null && !language.isEmpty()) {
            setRadioButtonLanguage(language);
        }

    }

    private String getRadioChangeLanguage() {
        int checkedRadioButtonId = rgChangeLanguage.getCheckedRadioButtonId();
        language = null;
        if (checkedRadioButtonId != -1) {
            switch (checkedRadioButtonId) {
                case R.id.rb_indonesia:
                    language = "id";
                    break;
                case R.id.rb_english:
                    language = "en-US";
                    break;
            }
        }
        return language;
    }

    private void finishChangeLanguage() {
        saveLanguage(getRadioChangeLanguage());
        setLanguage();
    }


    @Override
    public void onBackPressed() {
        finishChangeLanguage();
        super.onBackPressed();
    }

    private void setRadioButtonLanguage(String language) {
        if (language != null) {
            switch (language) {
                case "id":
                    rbIndonesia.setChecked(true);
                    break;
                case "en-US":
                    rbEnglish.setChecked(true);
                    break;
            }
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
}