package com.chenxulu.myapps.expand;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.chenxulu.myapps.R;

import java.util.ArrayList;

/**
 * Created by xulu on 16/5/26.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {
    private ArrayList<ClassItem> classList;

    public ExpandAdapter(ArrayList<ClassItem> classItems) {
        this.classList = classItems;
    }

    @Override
    public int getGroupCount() {
        return classList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return classList.get(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_expend_group_item, null);
            holder = new GroupHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.textView.setText(classList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return classList.get(groupPosition).getStudents().size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return classList.get(groupPosition).getStudents().get(childPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_expend_child_item, null);
            holder = new ChildHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        Student student = classList.get(groupPosition).getStudents().get(childPosition);
        holder.textView.setText(student.getName());
        return convertView;
    }

    class GroupHolder {
        TextView textView;
    }

    class ChildHolder {
        TextView textView;
    }
}

