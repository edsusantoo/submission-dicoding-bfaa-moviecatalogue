package com.edsusantoo.bismillah.moviecatalogue.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.data.network.observer.ApiSingleObserver;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;
import com.edsusantoo.bismillah.moviecatalogue.utils.HelperNotification;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReleaseReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String api_key = intent.getStringExtra(Constant.EXTRAS_API_KEY);
        String language = intent.getStringExtra(Constant.EXTRAS_LANGUAGE);
        getMovie(api_key, language, context);
    }

    private void getMovie(String api_key, String language, final Context context) {
        //getmovie
        MoviesRepository repository = new MoviesRepository(context);
        repository.getMovie(api_key, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSingleObserver<MovieResponse>(repository.getCompositeDisposable()) {
                    @Override
                    public void onSuccessful(MovieResponse response) {
                        for (ResultsItem data : response.getResults()) {
                            if (data.getReleaseDate().equals(getDateNow())) {
                                HelperNotification.ChooseNotification(context, data.getTitle() + " Release Today !!!", "Release Reminder", "text", null, null, null, null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });
    }

    private String getDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date calendar = Calendar.getInstance().getTime();
        return dateFormat.format(calendar);
    }

}
