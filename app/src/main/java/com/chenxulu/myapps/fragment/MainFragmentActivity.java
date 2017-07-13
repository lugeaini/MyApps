package com.chenxulu.myapps.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.chenxulu.myapps.R;

public class MainFragmentActivity extends AppCompatActivity implements BoardFragment.OnFragmentInteractionListener {
    private View chatLayout;
    private View boardLayout;
    private View videoLayout;

    private ChatFragment chatFragment;
    private BoardFragment boardFragment;
    private VideoFragment videoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePosition();
            }
        });

        chatLayout = findViewById(R.id.chat_fragment);
        boardLayout = findViewById(R.id.board_fragment);
        videoLayout = findViewById(R.id.video_fragment);

        initTopBoardLayout();
        initVideoLayout();
        initChatLayout();

        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (boardFragment == null) {
            boardFragment = BoardFragment.newInstance("", "");
        }
        transaction.add(R.id.board_fragment, boardFragment);

        if (chatFragment == null) {
            chatFragment = ChatFragment.newInstance("", "");
        }
        transaction.add(R.id.chat_fragment, chatFragment);

        if (videoFragment == null) {
            videoFragment = VideoFragment.newInstance();
        }
        transaction.add(R.id.video_fragment, videoFragment);

        transaction.commit();
    }

    private void initTopBoardLayout() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) boardLayout.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = (int) (layoutParams.width / 16.0 * 9);
        layoutParams.setMargins(0, 0, 0, 0);
        boardLayout.setLayoutParams(layoutParams);
    }

    private void initVideoLayout() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) videoLayout.getLayoutParams();
        layoutParams.width = screenWidth / 3;
        layoutParams.height = (int) (layoutParams.width / 16.0 * 9);
        int left = screenWidth - layoutParams.width;
        int top = (int) (screenWidth / 16.0 * 9);
        layoutParams.setMargins(left, top, 0, 0);
        videoLayout.setLayoutParams(layoutParams);
    }

    private void initChatLayout() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) chatLayout.getLayoutParams();
        int top = (int) (screenWidth / 16.0 * 9);
        layoutParams.setMargins(0, top, 0, 0);
        chatLayout.setLayoutParams(layoutParams);
    }

    private void changePosition() {
        setBoardLayout();
        setVideoLayout();
    }

    private void setBoardLayout() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) boardLayout.getLayoutParams();
        layoutParams.width = screenWidth / 3;
        layoutParams.height = (int) (layoutParams.width / 16.0 * 9);
        layoutParams.setMargins(videoLayout.getLeft(), videoLayout.getTop(), 0, 0);
        boardLayout.setLayoutParams(layoutParams);
    }

    private void setVideoLayout() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) videoLayout.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = (int) (layoutParams.width / 16.0 * 9);
        layoutParams.setMargins(0, 0, 0, 0);
        videoLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
