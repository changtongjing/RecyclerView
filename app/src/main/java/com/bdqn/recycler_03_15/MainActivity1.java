package com.bdqn.recycler_03_15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bdqn.recycler_03_15.adapter.MyAdapter1;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter1 adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //设置布局
//        RecyclerView.LayoutManager manager = new GridLayoutManager(this,4, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(manager);

        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        addData();

        //适配器
        adapter1 = new MyAdapter1(mData,this);

        //展示数据
        mRecyclerView.setAdapter(adapter1);

        adapter1.setiGetClick(new MyAdapter1.IGetClick() {
            @Override
            public void onItemClick(int position, View itemView) {
                Toast.makeText(MainActivity1.this, "点击事件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position, View itemView) {
                Toast.makeText(MainActivity1.this, "长按事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //添加数据
    private void addData() {
        for (int i = 'A'; i < 'z'; i++) {
            mData.add(String.valueOf((char)i));
        }
    }

    //添加按钮 和 删除按钮
    public void btn(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                adapter1.addData(1,"你点啊");
                break;
            case R.id.btn_delete:
                adapter1.deleteData(1);
                break;
        }
    }
}
