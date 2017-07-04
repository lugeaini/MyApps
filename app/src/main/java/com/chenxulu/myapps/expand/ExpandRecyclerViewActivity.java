package com.chenxulu.myapps.expand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.chenxulu.myapps.R;

public class ExpandRecyclerViewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }

}
