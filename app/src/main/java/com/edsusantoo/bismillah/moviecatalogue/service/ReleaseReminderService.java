package com.edsusantoo.bismillah.moviecatalogue.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class ReleaseReminderService extends Service {
    public static final long DEFAULT_SYNC_INTERVAL = 10 * 1000;//10 second
    private Handler handler;
    private String api_key;
    private String language;
    //untuk menjalankan receiver setiap 10detik
    private Runnable runnableService = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(runnableService, DEFAULT_SYNC_INTERVAL);
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        handler.post(runnableService);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
