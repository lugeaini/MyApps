package com.chenxulu.myapps.drag;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.chenxulu.library.utils.DeviceUtil;
import com.chenxulu.myapps.R;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class DragGridActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private MyScrollView scrollView;
    private DragGridView gridView;

    MyGridAdapter mAdapter;
    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_grid);
        initButton();
        gridView = (DragGridView) findViewById(R.id.drag_grid_view);
        gridView.setNumColumns(3);
        gridView.setOnItemLongClickListener(this);

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(i + "");
        }
        mAdapter = new MyGridAdapter(mList);
        gridView.setAdapter(mAdapter);
        gridView.setDragGridViewListener(mAdapter);

        int displayWidth = DeviceUtil.getDisplayWidth(this);
        int count = mAdapter.getCount();
        int height = displayWidth / 3 * ((count - 1) / 3 + 1);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = height;
        gridView.setLayoutParams(layoutParams);

        scrollView = (MyScrollView) findViewById(R.id.scroll_view);
        scrollView.setDragView(gridView);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("onItemLongClick");
        gridView.startDrag(view, position);
        return false;
    }

    private void initButton() {
        final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("FirstVisiblePosition:" + gridView.getFirstVisiblePosition());
                System.out.println("ChildCount:" + gridView.getChildCount());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}
