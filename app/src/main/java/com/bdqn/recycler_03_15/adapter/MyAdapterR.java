package com.bdqn.recycler_03_15.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bdqn.recycler_03_15.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyAdapterR extends RecyclerView.Adapter<MyAdapterR.MyHolder> {

    private List<String> mData;
    private Context context;
    private List<Integer> height = new ArrayList<>();

    public MyAdapterR(List<String> mData, Context context) {
        this.mData = mData;
        this.context = context;
        //瀑布流的效果
        for (int i = 0; i < mData.size(); i++) {
            int heightOne = (int)(100 + Math.random() * 300);
            height.add(heightOne);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_refresh,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.textView.setText(mData.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iGetClick.onItemClick(position,holder.itemView);
            }
        });
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                iGetClick.onLongItemClick(position,holder.itemView);
                return true;
            }
        });
        holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height.get(position)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    //通过接口的方式获得点击事件
    public interface IGetClick{
        void onItemClick(int position, View itemView);
        void onLongItemClick(int position, View itemView);
    }

    private IGetClick iGetClick;

    public void setiGetClick(IGetClick iGetClick) {
        this.iGetClick = iGetClick;
    }

    //配合添加数据和删除数据的操作
    public void addData(int position,String data){
        mData.add(position,data);
        int heightOne = (int)(100 + Math.random() * 300);
        height.add(position,heightOne);
        notifyItemInserted(position);
    }
    public void deleteData(int position){
        mData.remove(position);
        height.remove(position);
        notifyItemRemoved(position);
    }
}















