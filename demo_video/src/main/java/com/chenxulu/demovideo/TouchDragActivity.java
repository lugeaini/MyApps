package com.chenxulu.demovideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;

public class TouchDragActivity extends AppCompatActivity implements View.OnTouchListener {
    String videoPath = "http://file.ihimee.cn/videoForMobile/0/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD/%E8%8B%B1%E6%96%87%E8%A7%86%E9%A2%91/%E8%8B%B1%E6%96%87%E7%BB%98%E6%9C%AC/Cbeebies%E7%B3%BB%E5%88%97/006%20and%20a%20Bit.mp4";

    private ListView listView;

    private MyVideoLayout videoLayout;

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

        listView = (ListView) findViewById(R.id.list_view);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        listView.setAdapter(new MyAdapter(list));
        listView.setVisibility(View.GONE);

        videoLayout = (MyVideoLayout)findViewById(R.id.video_layout);
        videoLayout.setOnTouchListener(this);
        videoLayout.setVideoPath(videoPath);
        videoLayout.startPlay();
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
