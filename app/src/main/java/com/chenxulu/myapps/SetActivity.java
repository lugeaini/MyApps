package com.chenxulu.myapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.chenxulu.library.widget.MySetItemView;

public class SetActivity extends AppCompatActivity {
    private MySetItemView itemView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        itemView8 = (MySetItemView)findViewById(R.id.item8);
        itemView8.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("-------");
            }
        });
    }
}
