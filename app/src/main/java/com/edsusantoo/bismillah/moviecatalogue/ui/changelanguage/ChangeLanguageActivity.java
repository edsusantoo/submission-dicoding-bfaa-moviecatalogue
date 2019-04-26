package com.edsusantoo.bismillah.moviecatalogue.ui.changelanguage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.edsusantoo.bismillah.moviecatalogue.R;

public class ChangeLanguageActivity extends AppCompatActivity {
    public static final String EXTRA_LANGUAGE = "extra_language";
    private String language = null;
    private RadioGroup rgChangeLanguage;
    private RadioButton rbIndonesia, rbEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        Toolbar toolbar = findViewById(R.id.toolbar);
        rgChangeLanguage = findViewById(R.id.rg_change_language);
        rbIndonesia = findViewById(R.id.rb_indonesia);
        rbEnglish = findViewById(R.id.rb_english);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishChangeLanguage();
                finish();
            }
        });

        setRadioButtonLanguage();

    }

    private void setRadioButtonLanguage() {
        language = getIntent().getStringExtra(EXTRA_LANGUAGE);
        if (language != null) {
            switch (language) {
                case "in":
                    rbIndonesia.setChecked(true);
                    break;
                case "en":
                    rbEnglish.setChecked(true);
                    break;
            }
        }
    }

    private String getRadioChangeLanguage() {
        int checkedRadioButtonId = rgChangeLanguage.getCheckedRadioButtonId();
        language = null;
        if (checkedRadioButtonId != -1) {
            switch (checkedRadioButtonId) {
                case R.id.rb_indonesia:
                    language = "in";
                    break;
                case R.id.rb_english:
                    language = "en";
                    break;
            }
        }
        return language;
    }

    private void finishChangeLanguage() {
        Intent i = new Intent();
        i.putExtra(EXTRA_LANGUAGE, getRadioChangeLanguage());
        setResult(RESULT_OK, i);
    }


    @Override
    public void onBackPressed() {
        finishChangeLanguage();
        super.onBackPressed();
    }
}
