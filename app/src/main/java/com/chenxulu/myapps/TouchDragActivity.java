package com.chenxulu.myapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TouchDragActivity extends AppCompatActivity implements View.OnTouchListener {
    private ListView listView;
    private ImageView icon;
    private View layout;

    private int displayWidth;
    private int displayHeight;
    private float lastX;
    private float lastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_drag);

        displayWidth = getResources().getDisplayMetrics().widthPixels;
        displayHeight = getResources().getDisplayMetrics().heightPixels;

        listView = (ListView)findViewById(R.id.list_view);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        listView.setAdapter(new MyAdapter(list));

        layout = findViewById(R.id.drag_layout);
        layout.setOnTouchListener(this);
        icon = (ImageView) findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>><<<<<<<<<<<<");
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - lastX;
                float dy = event.getRawY() - lastY;
                lastX = event.getRawX();
                lastY = event.getRawY();

                int left = (int) (v.getLeft() + dx);
                int top = (int) (v.getTop() + dy);
                int right = (int) (v.getRight() + dx);
                int bottom = (int) (v.getBottom() + dy);
                System.out.println(">>>>>>" + displayWidth + "-" + displayHeight);
                System.out.println(">>>>>>" + left + "-" + right + "-" + top + "-" + bottom);

                if (left > 0 && right < displayWidth && top > 0 && bottom < displayHeight) {
                    v.layout(left, top, right, bottom);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return true;
    }
}
