/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.util;

import com.fast.framework.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Created by lishicong on 2017/6/19.
 */

public class NotifiUtil {

    public static void showNotifyWidget(Context context, RemoteViews remoteViews) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        PendingIntent pendingIntent = getDefalutIntent(context, Notification.FLAG_ONGOING_EVENT);

        mBuilder.setContent(remoteViews);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setTicker("ticker");
        mBuilder.setPriority(Notification.PRIORITY_DEFAULT);
        mBuilder.setOngoing(true);
        mBuilder.setSmallIcon(R.mipmap.ic_share_friends);

        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;

        notificationManager.notify(200, notify);
    }

    public static PendingIntent getDefalutIntent(Context context, int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, new Intent(), flags);
        return pendingIntent;
    }
}
