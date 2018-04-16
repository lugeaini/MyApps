package com.chenxulu.myapps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chenxulu.myapps.fragment.BlankFragment;
import com.chenxulu.myapps.fragment.ScrollFragment;

/**
 * @author xulu
 */
public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab " + 0));
        tabLayout.addTab(tabLayout.newTab().setText("Tab " + 1));
        tabLayout.addTab(tabLayout.newTab().setText("Tab " + 2));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ScrollFragment scrollFragment = new ScrollFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_layout, scrollFragment)
                .commit();

        BlankFragment blankFragment = new BlankFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.top_layout, blankFragment)
                .commit();

    }
}
