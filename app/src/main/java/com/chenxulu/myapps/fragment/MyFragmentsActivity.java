package com.chenxulu.myapps.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.model.Student;

/**
 * @author xulu
 */
public class MyFragmentsActivity extends AppCompatActivity {
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragments);
        student = new Student("11", "22");

        MyFragment fragment = MyFragment.getInstance(student);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_layout, fragment)
                .commit();

        Bundle bundle = new Bundle();
        bundle.putParcelable("Student", student);

        Student item = bundle.getParcelable("Student");
        item.setId("44");

        System.out.println(student.getId());
    }

    public void setStudent() {
        System.out.println(student.getId());
    }

    public static class MyFragment extends Fragment {

        public MyFragment() {

        }

        public static MyFragment getInstance(Student student) {
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Student", student);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Student student = getArguments().getParcelable("Student");
            student.setId("33");
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {

            ((MyFragmentsActivity) getActivity()).setStudent();
            return new View(getActivity());
        }
    }
}
