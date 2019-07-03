package com.edsusantoo.bismillah.moviecatalogue.ui.widget.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.edsusantoo.bismillah.moviecatalogue.ui.widget.adapter.StackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}
