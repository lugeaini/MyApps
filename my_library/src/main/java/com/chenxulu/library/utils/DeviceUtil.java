package com.chenxulu.library.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
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

    /**
     * 获取状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
}
