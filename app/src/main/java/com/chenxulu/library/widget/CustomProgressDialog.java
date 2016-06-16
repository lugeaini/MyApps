package com.chenxulu.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenxulu.myapps.R;

public class CustomProgressDialog extends Dialog {

    private LinearLayout layout;
    private TextView titleTxt;
    private ImageView progress;

    public CustomProgressDialog(Context context) {
        super(context);
        init();
    }

    public CustomProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundResource(android.R.color.black);
        setContentView(layout);
        initProgress(layout);
        initTitle(layout);
    }

    private void initProgress(LinearLayout mLayout) {
        progress = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        mLayout.addView(progress, params);
        setAnim(progress);
    }

    private void setAnim(ImageView progress) {
        Animation anim = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(1000);// 持续1秒
        anim.setRepeatMode(Animation.RESTART);// 重复
        anim.setRepeatCount(Animation.INFINITE);// 无限次
        anim.setInterpolator(new LinearInterpolator());
        progress.setImageResource(R.drawable.spinner_48_outer_holo);
        progress.setAnimation(anim);
        anim.start();
    }

    private void initTitle(LinearLayout mLayout) {
        titleTxt = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 0, 20, 20);
        titleTxt.setGravity(Gravity.CENTER);
        titleTxt.setTextColor(Color.WHITE);
        mLayout.addView(titleTxt, params);
    }

    public void setTitle(String title) {
        titleTxt.setText(title);
    }

    public void setBackbround(int id) {
        layout.setBackgroundResource(id);
    }

    public void setOrientation(int orientation) {
        if (orientation == LinearLayout.HORIZONTAL) {
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER_VERTICAL);
        } else {
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }

    @Override
    public void show() {
        super.show();
        setAnim(progress);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
