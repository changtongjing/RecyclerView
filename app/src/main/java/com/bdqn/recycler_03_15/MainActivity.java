package com.bdqn.recycler_03_15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bdqn.recycler_03_15.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //设置布局
        RecyclerView.LayoutManager manager = new GridLayoutManager(this,4, LinearLayoutManager.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(manager);

        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        addData();

        //适配器
        adapter = new MyAdapter(mData,this);

        //展示数据
        mRecyclerView.setAdapter(adapter);

        adapter.setiGetClick(new MyAdapter.IGetClick() {
            @Override
            public void onItemClick(int position, View itemView) {
                Toast.makeText(MainActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position, View itemView) {
                Toast.makeText(MainActivity.this, "长按事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //添加数据
    private void addData() {
        for (int i = 'A'; i < 'z'; i++) {
            mData.add(String.valueOf((char)i));
        }
    }
}
