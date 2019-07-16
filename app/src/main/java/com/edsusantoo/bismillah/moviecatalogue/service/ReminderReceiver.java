package com.edsusantoo.bismillah.moviecatalogue.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;
import com.edsusantoo.bismillah.moviecatalogue.utils.HelperNotification;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(Constant.INTENT_DATA_TITLE);
        String message = intent.getStringExtra(Constant.INTENT_DATA_MESSAGE);

        HelperNotification.ChooseNotification(context, message, title, "text", null, null, null);
    }
}
