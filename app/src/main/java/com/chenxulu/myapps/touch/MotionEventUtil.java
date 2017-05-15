package com.chenxulu.myapps.touch;

import android.view.MotionEvent;

/**
 * Created by xulu on 16/8/11.
 */
public class MotionEventUtil {

    public static String getMotionEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return "ACTION_DOWN";
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return "ACTION_MOVE";
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            return "ACTION_UP";
        } else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
            return "ACTION_CANCEL";
        }
        return String.valueOf(ev.getAction());
    }

    public static void log(String tag, MotionEvent ev) {
        System.out.println(tag + ":" + getMotionEvent(ev));
    }

}
