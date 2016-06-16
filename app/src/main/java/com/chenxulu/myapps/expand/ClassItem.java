package com.chenxulu.myapps.expand;

import java.util.ArrayList;

/**
 * Created by xulu on 16/5/26.
 */
public class ClassItem {
    private String id;
    private String name;
    private ArrayList<Student> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
