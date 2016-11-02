package com.chenxulu.myapps.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.chenxulu.myapps.R;

public class AnimationActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView = (ImageView) findViewById(R.id.image_view);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawableAnimation();
                //rotateAnimation();
                animationSet();
            }
        });
    }

    private void animationSet() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_drawable_refreshing);
        imageView.setImageDrawable(animationDrawable);
        animationDrawable.start();
        int duration = animationDrawable.getDuration(0) * animationDrawable.getNumberOfFrames();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateAnimation();
            }
        }, duration);
    }

    private void drawableAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_drawable_refreshing);
        imageView.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }

    private void rotateAnimation() {
        imageView.setImageResource(R.drawable.icon_arrow_up);
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
        imageView.startAnimation(rotateAnimation);
    }
}
