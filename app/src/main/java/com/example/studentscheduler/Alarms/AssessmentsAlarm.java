package com.example.studentscheduler.Alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.studentscheduler.R;

public class AssessmentsAlarm extends BroadcastReceiver {
    // TODO: add paths
    public static final String ALARM_NOTIFICATION_TITLE = "";
    public static final String ALARM_NOTIFICATION_TEXT = "";
    public static final String ALARM_NOTIFICATION_TYPE = "";
    private static final int NOTIFY_ID_ASSESSMENT_ALARM = 1;
    public static final String ASSESSMENT_CHANNEL_ID_ALARMS = "CHANNEL_ID_COURSE_ALARMS";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context.getApplicationContext());

        String notificationTitle = intent.getStringExtra(ALARM_NOTIFICATION_TITLE);
        String notificationType = intent.getStringExtra(ALARM_NOTIFICATION_TYPE);
        String notificationText = intent.getStringExtra(ALARM_NOTIFICATION_TEXT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), ASSESSMENT_CHANNEL_ID_ALARMS)
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setContentTitle(intent.getStringExtra(ALARM_NOTIFICATION_TITLE))
                .setContentText(notificationTitle + " " + notificationType + " upcoming assessment on " + notificationText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManagerCompat.notify(NOTIFY_ID_ASSESSMENT_ALARM, builder.build());
    }
}
