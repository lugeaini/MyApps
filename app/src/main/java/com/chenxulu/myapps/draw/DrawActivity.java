package com.chenxulu.myapps.draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenxulu.myapps.R;

public class DrawActivity extends AppCompatActivity {
    private MyDrawView myDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        myDrawView = (MyDrawView) findViewById(R.id.image_view);
    }


}
