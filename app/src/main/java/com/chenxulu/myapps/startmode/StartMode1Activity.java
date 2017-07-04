package com.chenxulu.myapps.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chenxulu.library.activity.BaseLibraryActivity;
import com.chenxulu.myapps.R;

public class StartMode1Activity extends BaseLibraryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mode1);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartMode1Activity.this, StartMode2Activity.class));
            }
        });
    }
}
