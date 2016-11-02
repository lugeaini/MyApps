package com.chenxulu.library.utils;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Secure;

public class DeviceUtil {

    /**
     * screen width
     */
    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * screen height
     */
    public static int getDisplayHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * IMID
     */
    public static String getDeviceId(Context context) {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    /**
     * External Storage State
     *
     * @return
     */
    public static boolean sdcardExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
