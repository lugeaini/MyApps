package com.chenxulu.myapps.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenxulu.myapps.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    /**
     * 显示通知
     *
     * @param context
     */
    public void showNotification(Context context) {

        String title = "标题";
        String content = "内容";
        Intent mIntent = new Intent(context, NotificationActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int functionType = 0;
        PendingIntent contentIntent = PendingIntent.getActivity(context, functionType,
                mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification(R.mipmap.ic_launcher,
                context.getString(R.string.app_name),
                System.currentTimeMillis());
        notification.defaults = Notification.DEFAULT_SOUND;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(functionType, notification);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setContentIntent(contentIntent);
    }
}
