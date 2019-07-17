package com.edsusantoo.bismillah.moviecatalogue.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;
import com.edsusantoo.bismillah.moviecatalogue.utils.HelperNotification;

import java.util.ArrayList;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(Constant.INTENT_DATA_TITLE);
        String message = intent.getStringExtra(Constant.INTENT_DATA_MESSAGE);
        ArrayList<String> arrayMessageMovie = intent.getStringArrayListExtra(Constant.INTENT_DATA_ARRAY_MESSAGE);

        if (message != null) {
            HelperNotification.ChooseNotification(context, message, title, "text", null, null, null, null);
        } else if (arrayMessageMovie != null) {
            HelperNotification.ChooseNotification(context, null, title, "text", null, null, null, arrayMessageMovie);
        }
    }
}
