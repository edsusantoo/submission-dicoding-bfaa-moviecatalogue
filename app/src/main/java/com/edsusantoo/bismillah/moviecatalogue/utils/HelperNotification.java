package com.edsusantoo.bismillah.moviecatalogue.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.edsusantoo.bismillah.moviecatalogue.R;

import java.util.Random;


public class HelperNotification {

    private static final String CHANNEL_ID_NOTIFICATION = "com.edsusantoo.bismillah.moviecatalogue";
    private static final String TYPE_TEXT = "text";
    private static final String TYPE_TEXT_LARGE = "text_large";
    private static final String TYPE_PICTURE = "picture";

    public static void ChooseNotification(Context context, String body, String title, String type, String urlPicture, String textLarge, PendingIntent pendingIntent) {
        if (type.equals(TYPE_TEXT_LARGE) && textLarge != null) {
            textLargeNotification(context, title, body, textLarge);
        } else if (type.equals(TYPE_PICTURE) && urlPicture != null) {
            pictureNotification(context, title, body, urlPicture);
        } else if (type.equals(TYPE_TEXT)) {
            textNotification(context, title, body);
        }
    }

    /**
     * text  notif
     */
    private static void textNotification(Context context, String title, String body) {
        int notificationId = new Random().nextInt();
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification =
                setupNotification(context, title, body, null, 0, null, null,
                        R.color.colorPrimary, true, null, CHANNEL_ID_NOTIFICATION,
                        Notification.DEFAULT_ALL, Notification.PRIORITY_HIGH, defaultSoundUri, null, null);
        if (notification != null) {
            showNotification(context, notificationId, notification.build());
        }
    }

    /**
     * Text Large Notif
     */
    private static void textLargeNotification(Context context, String title, String body, String text_large) {
        int notificationId = new Random().nextInt();
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification =
                setupNotification(context, title, body, text_large, 0, null, null,
                        R.color.colorPrimary, true, null, CHANNEL_ID_NOTIFICATION,
                        Notification.DEFAULT_ALL, Notification.PRIORITY_HIGH, defaultSoundUri, null, null);
        if (notification != null) {
            showNotification(context, notificationId, notification.build());
        }
    }


    /**
     * Picture notification
     */

    private static void pictureNotification(Context context, String title, String body, String urlpicture) {
        int notificationId = new Random().nextInt();
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification =
                setupNotification(context, title, body, null, 0, urlpicture, urlpicture,
                        R.color.colorPrimary, true, null, CHANNEL_ID_NOTIFICATION,
                        Notification.DEFAULT_ALL, Notification.PRIORITY_HIGH, defaultSoundUri, null, null);
        if (notification != null) {
            showNotification(context, notificationId, notification.build());
        }
    }

    /**
     * Setup Notificaiton Builder
     *
     * @param context       context
     * @param title         judul si notif
     * @param message       pesannya atau body notif
     * @param smallIcon     icon kecil yang ada di statusbar
     * @param urlLargeIcon  icon besar
     * @param colorID       warna text aplikasi di notif
     * @param isAutoCancel  true agar notifnya bisa banyak, false niban keknya
     * @param contentIntent agar jika diklik notifnya bisa intent ke suatu activity
     * @param channelId     channel id si notif
     * @param isDefault     a
     * @param isPriority    prioritas notif
     * @param uriSound      untuk notif ada suaranya
     */
    private static NotificationCompat.Builder setupNotification(Context context, String title, String message, String textLarge,
                                                                int smallIcon, String urlLargeIcon, String urlBigPicture, int colorID,
                                                                boolean isAutoCancel, PendingIntent contentIntent,
                                                                String channelId, int isDefault, int isPriority, Uri uriSound, String groupName,
                                                                NotificationCompat.InboxStyle inboxStyle) {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
            builder.setAutoCancel(isAutoCancel);
            builder.setDefaults(isDefault);
            builder.setPriority(isPriority);

            if (!TextUtils.isEmpty(title))
                builder.setContentTitle(title);
            if (!TextUtils.isEmpty(message))
                builder.setContentText(message);


            if (!TextUtils.isEmpty(textLarge)) {
                builder.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(textLarge));
            }

            if (smallIcon != 0)
                builder.setSmallIcon(smallIcon);

            if (colorID != 0)
                builder.setColor(ContextCompat.getColor(context, colorID));

            if (contentIntent != null)
                builder.setContentIntent(contentIntent);

            if (uriSound != null)
                builder.setSound(uriSound);

            if (groupName != null) {
                builder.setGroup(groupName);
                builder.setGroupSummary(true);
            }

            if (inboxStyle != null) {
                builder.setStyle(inboxStyle);
            }

            return builder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Setup Show Notification
     *
     * @param context      conttext
     * @param notiID       id notif
     * @param notification random int
     */
    private static void showNotification(Context context, int notiID, Notification notification) {
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_NOTIFICATION,
                    "HISS",
                    NotificationManager.IMPORTANCE_DEFAULT);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }
        if (mNotificationManager != null) {
            mNotificationManager.notify(notiID, notification);
        }
    }


}
