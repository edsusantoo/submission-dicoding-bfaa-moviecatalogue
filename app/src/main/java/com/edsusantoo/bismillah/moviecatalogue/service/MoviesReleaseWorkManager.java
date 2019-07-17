package com.edsusantoo.bismillah.moviecatalogue.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

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

import static android.content.Context.ALARM_SERVICE;
import static com.edsusantoo.bismillah.moviecatalogue.ui.settings.SettingsActivity.REQUEST_CODE_RELEASE_REMINDER;

public class MoviesReleaseWorkManager extends Worker {
    public final static String TAG = MoviesReleaseWorkManager.class.getSimpleName();

    private Context context;

    public MoviesReleaseWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            String api_key = getInputData().getString(Constant.EXTRAS_API_KEY);
            String language = getInputData().getString(Constant.EXTRAS_LANGUAGE);


            //mengecek data intent dari activity
            if (api_key == null && language == null) {
                return Result.failure();
            } else if (api_key != null && language != null && language.isEmpty() && api_key.isEmpty()) {
                return Result.failure();
            }

            //mengecek jika proses async berhasil atau mengulang kembali
            if (getMovies(api_key, language)) {
                return Result.success();
            } else {
                return Result.retry();
            }

        } catch (Throwable e) {
            Log.e(TAG, "Error WorkManager :", e);
            return Result.failure();
        }
    }

    private boolean getMovies(String api_key, String language) {
        final boolean[] status = new boolean[1];
        MoviesRepository repository = new MoviesRepository(context);
        repository.getMovie(api_key, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSingleObserver<MovieResponse>(repository.getCompositeDisposable()) {
                    @Override
                    public void onSuccessful(MovieResponse response) {
                        for (ResultsItem data : response.getResults()) {
                            if (data.getReleaseDate().equals(getDateNow())) {
                                setAlarm(context, response.getResults());
                                status[0] = true;
                            }
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        status[0] = false;
                    }
                });

        return status[0];
    }

    private void setAlarm(Context context, List<ResultsItem> listMovie) {
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

        //setalarm
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(Constant.INTENT_DATA_TITLE, "Release Reminder");
        intent.putStringArrayListExtra(Constant.INTENT_DATA_ARRAY_MESSAGE, data);
        PendingIntent pendingIntentDailyReminder = PendingIntent.getBroadcast(context, REQUEST_CODE_RELEASE_REMINDER, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendarAlarm = Calendar.getInstance();
        calendarAlarm.setTimeInMillis(System.currentTimeMillis());
        calendarAlarm.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendarAlarm.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendarAlarm.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        calendarAlarm.set(Calendar.HOUR_OF_DAY, 8);
        calendarAlarm.set(Calendar.MINUTE, 0);
        calendarAlarm.set(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendarAlarm.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentDailyReminder);
    }

    private String getDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date calendar = Calendar.getInstance().getTime();
        return dateFormat.format(calendar);
    }
}
