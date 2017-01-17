package com.chenxulu.myapps.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.recycle.base.MyRecycleAdapter;

import java.util.List;

/**
 * Created by xulu on 2016/12/29.
 */

public class DragRecycleAdapter extends MyRecycleAdapter implements DragViewListener {
    List<String> mList;
    Context context;

    public DragRecycleAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size() / 2)
            return 2;
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //System.out.println("onCreateViewHolder:parent.childCount(" + parent.getChildCount() + ")");
        if (viewType == 1) {
            View view = View.inflate(context, R.layout.layout_recycle_item01, null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            viewHolder.titleView = (TextView) view.findViewById(R.id.title_txt);
            viewHolder.btnView = (Button)view.findViewById(R.id.btn);
            return viewHolder;
        } else {
            View view = View.inflate(context, R.layout.layout_recycle_item02, null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //System.out.println("onBindViewHolder:" + position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("itemView:onLongClick()");
                onItemLongClickListener.onItemLongClick(position, v);
                return true;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("itemView:onClick()");
            }
        });
        if (viewHolder.btnView != null)
            viewHolder.btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("btn:onClick()");
                }
            });
        if (viewHolder.getItemViewType() == 1)
            viewHolder.titleView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void reorderItems(int oldPosition, int newPosition) {
        String data = mList.get(oldPosition);
        mList.remove(oldPosition);
        mList.add(newPosition, data);
        notifyDataSetChanged();
    }

    @Override
    public void setHideItem(int hidePosition) {

    }

    @Override
    public boolean canDrag(int position) {
        if (position > mList.size() / 2 - 1)
            return false;
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        Button btnView;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }


}
