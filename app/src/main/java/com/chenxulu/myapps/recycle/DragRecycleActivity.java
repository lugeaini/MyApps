package com.chenxulu.myapps.recycle;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.recycle.base.MyRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class DragRecycleActivity extends Activity implements MyRecycleAdapter.OnItemLongClickListener {
    MyDragRecycleView recyclerView;
    DragRecycleAdapter myRecycleAdapter;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView = (MyDragRecycleView) findViewById(R.id.recycle_view);

        final List<String> mList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mList.add(String.valueOf(i));
        }

        layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //System.err.print("getSpanSize:" + position);
                //System.err.println(" getSpanCount:" + layoutManager.getSpanCount());
                if (position == mList.size() / 2)
                    return layoutManager.getSpanCount();
                return 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        myRecycleAdapter = new DragRecycleAdapter(mList, this);
        myRecycleAdapter.setOnItemLongClickListener(this);

        recyclerView.setAdapter(myRecycleAdapter);
        recyclerView.setDragViewListener(myRecycleAdapter);
    }

    @Override
    public void onItemLongClick(int position, View view) {
        recyclerView.startDrag(view, position);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.setSpanCount(6);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager.setSpanCount(3);
        }
    }
}
