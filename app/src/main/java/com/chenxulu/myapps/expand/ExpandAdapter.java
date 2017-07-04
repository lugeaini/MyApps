package com.chenxulu.myapps.expand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.model.ClassItem;
import com.chenxulu.myapps.model.Student;

import java.util.ArrayList;

/**
 * Created by xulu on 16/5/26.
 */
public class ExpandAdapter extends BaseExpandableListAdapter implements PinnedHeaderExpandableListView.OnHeaderUpdateListener {
    private Context mContext;
    private ArrayList<ClassItem> mList;

    public ExpandAdapter(Context context, ArrayList<ClassItem> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.layout_expend_group_item, null);
            holder = new GroupHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.textView.setText(mList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getStudents().size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getStudents().get(childPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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
        Student student = mList.get(groupPosition).getStudents().get(childPosition);
        holder.textView.setText(student.getName());
        return convertView;
    }

    class GroupHolder {
        TextView textView;
    }

    class ChildHolder {
        TextView textView;
    }


    @Override
    public View getPinnedHeader() {
        View headView = View.inflate(mContext, R.layout.layout_expend_group_item, null);
        headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        GroupHolder holder = new GroupHolder();
        holder.textView = (TextView) headView.findViewById(R.id.txt);
        headView.setTag(holder);
        return headView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        ClassItem group = (ClassItem) getGroup(firstVisibleGroupPos);
        GroupHolder holder = (GroupHolder) headerView.getTag();
        holder.textView.setText(group.getName());
    }
}

