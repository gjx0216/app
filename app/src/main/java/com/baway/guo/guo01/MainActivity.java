package com.baway.guo.guo01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baway.guo.guo01.adapter.RecyAdapterone;
import com.baway.guo.guo01.adapter.RecyAdaptertwo;
import com.baway.guo.guo01.entity.User;
import com.baway.guo.guo01.entity.User1;
import com.baway.guo.guo01.pathUrl.PathPresenter;
import com.baway.guo.guo01.pathUrl.PathView;
import com.baway.guo.guo01.urlmvp.UrlPresenter;
import com.baway.guo.guo01.urlmvp.UrlView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UrlView, PathView {

    private String path = "http://www.zhaoapi.cn/product/getProductCatagory?cid=";
    private String url = "http://www.zhaoapi.cn/product/getCatagory";
    /**
     * 购物车
     */
    private RecyclerView mRecycle1;
    private RecyclerView mRecycle2;
    private int page =0;
    private List<User.DataBean> mData;
    private UrlPresenter mUrlPresenter;
    private RecyAdapterone mRecyAdapterone;
    private List<User1.DataBean> mList;
    private PathPresenter mPathPresenter;
    private RecyAdaptertwo mRecyAdaptertwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecycle1 = findViewById(R.id.recycle1);
        mRecycle2 = findViewById(R.id.recycle2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mRecycle1.setLayoutManager(linearLayoutManager);
        mUrlPresenter = new UrlPresenter(this);
        mUrlPresenter.getUrls(url);

        mPathPresenter = new PathPresenter(this);
        mPathPresenter.getUrls(path+page);


    }

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        User user = gson.fromJson(result, User.class);
        mData = user.getData();
        mRecyAdapterone = new RecyAdapterone(getApplicationContext(), mData);
        mRecycle1.setAdapter(mRecyAdapterone);
        mRecyAdapterone.setOnItemCliklitener(new RecyAdapterone.OnItemCliklitener() {
            @Override
            public void OnClik(int i) {
             //   Toast.makeText(getApplicationContext(), "" + i, Toast.LENGTH_SHORT).show();
                mPathPresenter.getUrls(path + i);
            }
        });
    }

    @Override
    public void onFalier(String msg) {
        Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPathSuccess(String result) {
        Gson gson = new Gson();
        User1 user1 = gson.fromJson(result, User1.class);
        mList = user1.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecycle2.setLayoutManager(gridLayoutManager);
        mRecyAdaptertwo = new RecyAdaptertwo(getApplicationContext(), mList);
        mRecycle2.setAdapter(mRecyAdaptertwo);
    }

    @Override
    public void onPathFalier(String msg) {
        Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_LONG).show();
    }
}
