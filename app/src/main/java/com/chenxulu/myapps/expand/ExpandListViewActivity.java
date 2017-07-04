package com.chenxulu.myapps.expand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.model.ClassItem;
import com.chenxulu.myapps.model.Student;

import java.util.ArrayList;
import java.util.Random;

public class ExpandListViewActivity extends AppCompatActivity {
    private PinnedHeaderExpandableListView mListView;
    private ExpandAdapter mAdapter;

    private ArrayList<ClassItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list_view);
        mListView = (PinnedHeaderExpandableListView) findViewById(R.id.expandableListView);
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                System.out.println("onChildClick");
                return false;
            }
        });

        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClassItem classItem = new ClassItem();
            classItem.setId(i + "");
            classItem.setName("班级" + i);
            ArrayList<Student> studentList = new ArrayList<>();
            classItem.setStudents(studentList);
            int max = new Random().nextInt(10);
            for (int j = 0; j < max; j++) {
                Student student = new Student();
                student.setId(j + "");
                student.setName("学员" + j);
                studentList.add(student);
            }
            mList.add(classItem);

        }

        mAdapter = new ExpandAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnHeaderUpdateListener(mAdapter);

        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            mListView.expandGroup(i);
        }
    }
}
