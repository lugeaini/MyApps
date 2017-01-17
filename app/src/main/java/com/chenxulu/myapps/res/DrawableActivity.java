package com.chenxulu.myapps.res;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chenxulu.myapps.R;

public class DrawableActivity extends Activity {
    ImageView logoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_res);
        logoView = (ImageView) findViewById(R.id.image_view);

        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("--");
                System.out.println("width:" + logoView.getWidth());
                System.out.println("height:" + logoView.getHeight());
            }
        });
    }
}
