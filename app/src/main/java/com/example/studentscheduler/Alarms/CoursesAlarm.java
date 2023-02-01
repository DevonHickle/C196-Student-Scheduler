package com.example.studentscheduler.Alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.studentscheduler.R;

public class CoursesAlarm extends BroadcastReceiver {
    // TODO: Add paths
    public static final String ALARM_NOTIFICATION_TITLE = "";
    public static final String ALARM_NOTIFICATION_TEXT = "";
    public static final String COURSE_CHANNEL_ID_ALARMS = "CHANNEL_ID_COURSE_ALARMS";
    public static final int NOTIFY_ID_COURSE_ALARM = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context.getApplicationContext());

        String notificationTitle = intent.getStringExtra(ALARM_NOTIFICATION_TITLE);
        String notificationText = intent.getStringExtra(ALARM_NOTIFICATION_TEXT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), COURSE_CHANNEL_ID_ALARMS)
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setContentTitle(notificationTitle)
                .setContentText(notificationTitle + " " + notificationText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManagerCompat.notify(NOTIFY_ID_COURSE_ALARM, builder.build());
    }
}
