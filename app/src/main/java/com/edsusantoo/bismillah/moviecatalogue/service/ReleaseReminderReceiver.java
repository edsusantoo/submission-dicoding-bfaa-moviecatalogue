package com.edsusantoo.bismillah.moviecatalogue.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edsusantoo.bismillah.moviecatalogue.data.MoviesRepository;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.ResultsItem;
import com.edsusantoo.bismillah.moviecatalogue.data.network.observer.ApiSingleObserver;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        final List<ResultsItem> listMovieRelease = new ArrayList<>();
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
                                listMovieRelease.add(data);
                                setReceiver(context, listMovieRelease);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });
    }

    private void setReceiver(Context context, List<ResultsItem> listMovie) {
        //copy tittle listmovie to new array string
        ArrayList<String> data = new ArrayList<>();
        for (ResultsItem resultsListMovie : listMovie) {
            data.add(resultsListMovie.getOriginalTitle());
        }

        //setcalendar releasedate
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            calendar.setTime(simpleDateFormat.parse(listMovie.get(0).getReleaseDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //setreceiver
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(Constant.INTENT_DATA_TITLE, "Release Reminder");
        intent.putStringArrayListExtra(Constant.INTENT_DATA_ARRAY_MESSAGE, data);
        context.sendBroadcast(intent);
    }

    private String getDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date calendar = Calendar.getInstance().getTime();
        return dateFormat.format(calendar);
    }

}
