package com.chenxulu.myapps.recycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.recycle.base.MyRecycleView;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleActivity extends AppCompatActivity {
    private MyRecycleView mRecycleView;

    private List<String> mList;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle);
        mRecycleView = (MyRecycleView) findViewById(R.id.recycle_view);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        mRecycleView.setNestedScrollingEnabled(false);

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(i + "");
        }
        mAdapter = new MyAdapter(this, mList);
        mRecycleView.setAdapter(mAdapter);
    }
}
