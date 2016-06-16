package com.chenxulu.myapps.expand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.chenxulu.myapps.R;

import java.util.ArrayList;
import java.util.Random;

public class ExpandListViewActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandAdapter expandAdapter;

    private ArrayList<ClassItem> classItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list_view);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        classItems = new ArrayList<>();
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
            classItems.add(classItem);

        }

        expandAdapter = new ExpandAdapter(classItems);
        expandableListView.setAdapter(expandAdapter);

        for (int i = 0; i < expandAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }
}
