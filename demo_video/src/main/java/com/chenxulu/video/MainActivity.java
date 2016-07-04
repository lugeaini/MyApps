package com.chenxulu.video;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements MyVideoController.VideoControllerListener {

    String videoPath = "http://file.ihimee.cn/videoForMobile/0/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD/%E8%8B%B1%E6%96%87%E8%A7%86%E9%A2%91/%E8%8B%B1%E6%96%87%E7%BB%98%E6%9C%AC/Cbeebies%E7%B3%BB%E5%88%97/006%20and%20a%20Bit.mp4";

    private View topLayout;
    private ListView listView;
    private View headLayout;
    private View headView;
    private MyVideoLayout myVideoLayout;
    private MyVideoController myVideoController;

    private ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_main);

        topLayout = findViewById(R.id.top_layout);
        headLayout = LayoutInflater.from(this).inflate(R.layout.layout_header_view, null);
        headView = headLayout.findViewById(R.id.head_video_view);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVideoController.startPlay();
            }
        });

        listView = (ListView) findViewById(R.id.list_view);
        listView.addHeaderView(headLayout);

        myVideoLayout = (MyVideoLayout) findViewById(R.id.video_player);
        myVideoController = new MyVideoController(myVideoLayout, listView, headLayout, headView);
        myVideoController.setVideoPath(videoPath);
        myVideoController.setMyVideoLayoutListener(this);

        listView.setOnScrollListener(myVideoController);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        listView.setAdapter(new MyAdapter(list));
    }

    @Override
    public void fullScreen(boolean fullScreen) {
        if (fullScreen) {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attr);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            listView.setVisibility(View.GONE);
            topLayout.setVisibility(View.GONE);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            listView.setVisibility(View.VISIBLE);
            topLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void playOnError() {
        Toast.makeText(this, "play error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myVideoController != null) {
            myVideoController.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myVideoController != null) {
            myVideoController.stop();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myVideoController.onConfigurationChanged();
    }

    @Override
    public void onBackPressed() {
        if (myVideoController != null && myVideoController.isFullScreen()) {
            myVideoController.stop();
            fullScreen(false);
        } else {
            finish();
        }
    }
}
