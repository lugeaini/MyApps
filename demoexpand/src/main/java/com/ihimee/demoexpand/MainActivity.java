package com.ihimee.demoexpand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    private ArrayList<ClassItem> classItems;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);

        classItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClassItem classItem = new ClassItem();
            classItem.setId(i + "");
            classItem.setName("班级" + i);
            classItem.setExpand(true);
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

        myAdapter = new MyAdapter(this,classItems);
        listView.setAdapter(myAdapter);
    }
}
