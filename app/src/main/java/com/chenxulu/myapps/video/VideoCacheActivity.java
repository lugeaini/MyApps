package com.chenxulu.myapps.video;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.application.VideoCacheApplication;
import com.danikula.videocache.HttpProxyCacheServer;

public class VideoCacheActivity extends AppCompatActivity {
    String videoPath = "http://file.ihimee.cn/videoForMobile/0/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD/%E8%8B%B1%E6%96%87%E8%A7%86%E9%A2%91/%E8%8B%B1%E6%96%87%E7%BB%98%E6%9C%AC/Cbeebies%E7%B3%BB%E5%88%97/006%20and%20a%20Bit.mp4";

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_cache);
        videoView = (VideoView) findViewById(R.id.video_view);

        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpProxyCacheServer proxy = VideoCacheApplication.getProxy(getBaseContext());
                videoView.setVideoPath(proxy.getProxyUrl(videoPath));
                videoView.requestFocus();
                videoView.start();
                v.setVisibility(View.GONE);
            }
        });
    }
}
