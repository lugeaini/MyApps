package com.chenxulu.myapps.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.chenxulu.myapps.R;

import java.util.ArrayList;

public class MultiTypeListActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<String> list;
    private MyMultiAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(R.id.list_view);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mAdapter = new MyMultiAdapter(list);
        mListView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("0");
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
