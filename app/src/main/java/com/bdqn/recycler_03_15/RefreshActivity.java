package com.bdqn.recycler_03_15;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bdqn.recycler_03_15.adapter.MyAdapterR;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapterR adapterR;
    private SwipeRefreshLayout mSwipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefresh);

        //设置布局
//        RecyclerView.LayoutManager manager = new GridLayoutManager(this,4, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(manager);

        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        addData();

        //适配器
        adapterR = new MyAdapterR(mData,this);

        //展示数据
        mRecyclerView.setAdapter(adapterR);

        adapterR.setiGetClick(new MyAdapterR.IGetClick() {
            @Override
            public void onItemClick(int position, View itemView) {
                Toast.makeText(RefreshActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position, View itemView) {
                Toast.makeText(RefreshActivity.this, "长按事件", Toast.LENGTH_SHORT).show();
            }
        });

        //设置小圆圈的颜色
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark,R.color.colorAccent);
        //刷新监听
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RefreshActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        //停止刷新
                        mSwipeRefresh.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    //延时
    Handler handler = new Handler();

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
                adapterR.addData(1,"你点啊");
                break;
            case R.id.btn_delete:
                adapterR.deleteData(1);
                break;
        }
    }
}
