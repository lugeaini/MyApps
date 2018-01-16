package com.chenxulu.myapps.animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.chenxulu.myapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity01 extends Activity {
    @BindView(R.id.title_txt)
    TextView textView;
    @BindView(R.id.layout)
    View layout;

    private LiveBarrageUtil barrageUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animatior01);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void start() {
        if (barrageUtil == null)
            barrageUtil = new LiveBarrageUtil(layout, textView);
        barrageUtil.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        barrageUtil.stop();
    }
}
