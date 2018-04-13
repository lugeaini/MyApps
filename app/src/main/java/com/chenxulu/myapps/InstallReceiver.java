package com.chenxulu.myapps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * The type Install receiver.
 *
 * @author xulu
 * @date 2017 /10/24.
 */
public class InstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            String packageName = intent.getDataString();
            if (packageName.contains(context.getApplicationInfo().packageName)) {
                startApp(context);
            }
        }

        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
            String packageName = intent.getDataString();
            if (packageName.contains(context.getApplicationInfo().packageName)) {
                startApp(context);
            }
        }
    }

    /**
     * 监测到升级后执行app的启动
     *
     * @param context the context
     */
    public void startApp(Context context) {
        Intent intent = new Intent(context, ScrollingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
