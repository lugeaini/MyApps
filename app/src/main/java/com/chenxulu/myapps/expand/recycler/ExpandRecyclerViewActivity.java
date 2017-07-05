package com.chenxulu.myapps.expand.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.model.ClassItem;
import com.chenxulu.myapps.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpandRecyclerViewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyExpandAdapter mAdapter;
    List<ClassItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

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

        mAdapter = new MyExpandAdapter(this,mList);
        mRecyclerView.setAdapter(mAdapter);

    }

}
