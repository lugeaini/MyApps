package com.ihimee.demoexpand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xulu on 16/6/7.
 */
public class MyAdapter extends BaseAdapter {
    protected ArrayList<ClassItem> list;
    protected Context context;

    public MyAdapter(Context context, ArrayList<ClassItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CategoryViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new CategoryViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout, null);
            viewHolder.categoryLayout = convertView.findViewById(R.id.category_layout);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.category_icon);
            viewHolder.title = (TextView) convertView.findViewById(R.id.category_title);
            viewHolder.personLayout = (LinearLayout) convertView.findViewById(R.id.person_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CategoryViewHolder) convertView.getTag();
        }
        final ClassItem classItem = list.get(position);
        ArrayList<Student> students = classItem.getStudents();

        // 数据和视图个数保持一致
        if (viewHolder.personLayout.getChildCount() > students.size()) {
            for (int i = 0; i < viewHolder.personLayout.getChildCount() - students.size(); i++) {
                viewHolder.personLayout.removeViewAt(i);
            }
        } else {
            for (int i = 0; i < students.size() - viewHolder.personLayout.getChildCount(); i++) {
                View childLayout = LayoutInflater.from(context).inflate(R.layout.layout_person, null);
                ChildViewHolder childHolder = new ChildViewHolder();
                childHolder.avatar = (ImageView) childLayout.findViewById(R.id.avatar);
                childHolder.name = (TextView) childLayout.findViewById(R.id.name);
                childLayout.setTag(childHolder);
                viewHolder.personLayout.addView(childLayout);
            }
        }

        for (int i = 0; i < students.size(); i++) {

        }

        if (classItem.isExpand()){
            viewHolder.personLayout.setVisibility(View.VISIBLE);
        }else {
            viewHolder.personLayout.setVisibility(View.GONE);
        }

        viewHolder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classItem.setExpand(!classItem.isExpand());
                if (classItem.isExpand()){
                    viewHolder.personLayout.setVisibility(View.VISIBLE);
                }else {
                    viewHolder.personLayout.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }

    class CategoryViewHolder {
        private LinearLayout personLayout;
        private View categoryLayout;
        private ImageView icon;
        private TextView title;
    }

    class ChildViewHolder {
        private ImageView avatar;
        private TextView name;
    }


}
