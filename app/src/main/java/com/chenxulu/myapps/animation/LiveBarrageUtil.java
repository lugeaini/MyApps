package com.chenxulu.myapps.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by xulu on 2017/9/7.
 */

public class LiveBarrageUtil {
    public static final int ARG = 1;

    private View layout;
    private TextView textView;

    private float[] locationArray = {0.3f, 0.6f};
    private int position;

    public LiveBarrageUtil(View layout, TextView textView) {
        this.layout = layout;
        this.textView = textView;
    }

    public void setLayout(View layout) {
        this.layout = layout;
    }

    public void start() {
        position = 0;
        handler.removeMessages(ARG);
        handler.sendEmptyMessage(ARG);
    }

    public void stop() {
        handler.removeMessages(ARG);
    }

    private void startAnimation() {
        int toXDelta = layout.getWidth() + textView.getWidth();
        TranslateAnimation animation = new TranslateAnimation(0, toXDelta, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(10 * 1000);
        textView.startAnimation(animation);

        long delay = new Random().nextInt(8) + 3;
        position = 1 - position;
        handler.sendEmptyMessageDelayed(ARG, delay * 60 * 1000);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == ARG) {
                textView.clearAnimation();
                updateView();
                startAnimation();
            }
        }
    };

    public void updateView() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        int left = -textView.getWidth();
        int top = (int) (layout.getHeight() * locationArray[position]);
        layoutParams.setMargins(left, top, 0, 0);
        textView.setLayoutParams(layoutParams);
    }
}
