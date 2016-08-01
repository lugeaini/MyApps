package com.chenxulu.myapps.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chenxulu.myapps.R;

/**
 * Created by xulu on 16/7/20.
 */
public class MergeView extends FrameLayout {
    private TextView txt;

    public MergeView(Context context) {
        this(context, null);
    }

    public MergeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MergeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_merge, this);
        txt = (TextView)findViewById(R.id.txt);
        txt.setText("aaa");
    }

}
