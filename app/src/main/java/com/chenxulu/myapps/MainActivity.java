package com.chenxulu.myapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    TextView titleTxt;

    @BindView(R.id.layout01)
    FrameLayout layout01;
    @BindView(R.id.layout02)
    FrameLayout layout02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        titleTxt = new TextView(this);
        titleTxt.setText(R.string.app_name);

        addView(layout01, titleTxt);
    }

    private void addView(FrameLayout viewGroup, View childView) {
        int width = FrameLayout.LayoutParams.WRAP_CONTENT;
        int height = FrameLayout.LayoutParams.WRAP_CONTENT;

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        viewGroup.addView(childView, layoutParams);
    }

    @OnClick(R.id.fab)
    void fab() {
        layout01.removeView(titleTxt);
        addView(layout02, titleTxt);
    }

}
