package com.edsusantoo.bismillah.moviecatalogue.ui.changelanguage;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.edsusantoo.bismillah.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeLanguageActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_change_language);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishChangeLanguage();
                finish();
            }
        });

        ChangeLanguageViewModel changeLanguageViewModel = ViewModelProviders.of(this).get(ChangeLanguageViewModel.class);

        if (changeLanguageViewModel.getLanguage() != null || changeLanguageViewModel.getLanguage().isEmpty()) {
            setRadioButtonLanguage(changeLanguageViewModel.getLanguage());
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
        Intent i = new Intent();
        i.putExtra(EXTRA_LANGUAGE, getRadioChangeLanguage());
        setResult(RESULT_OK, i);
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
}
