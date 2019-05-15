package com.edsusantoo.bismillah.moviecatalogue.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edsusantoo.bismillah.moviecatalogue.R;

public class NothingFilm extends LinearLayout {

    private TextView textView;
    private ImageView imageView;
    private Context context;

    public NothingFilm(Context context) {
        super(context);
        this.context = context;
    }

    public NothingFilm(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NothingFilm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NothingFilm(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        createTextView();
        createImage();
    }

    private void createTextView() {
        textView = new TextView(context);
        textView.setText(getResources().getString(R.string.nothing_film));
        addView(textView);
    }

    private void createImage() {
        imageView = new ImageView(context);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_broken_image_grey_100dp));
        addView(imageView);
    }
}
