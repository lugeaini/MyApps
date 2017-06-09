package com.chenxulu.myapps.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.recycle.base.MyRecycleAdapter;

import java.util.List;

/**
 * Created by xulu on 2017/5/15.
 */

public class MyAdapter extends MyRecycleAdapter {
    private Context mContext;
    private List<String> mList;

    public MyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item01, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        holder.textView = (TextView) view.findViewById(R.id.title_txt);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.textView.setText("");
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
