package com.baway.guo.gouwuche;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.baway.guo.gouwuche.adapter.ExAdapter;
import com.baway.guo.gouwuche.adapter.GoodsEntity;
import com.baway.guo.gouwuche.me.Subview;
import com.baway.guo.gouwuche.url.UrlPresenter;
import com.baway.guo.gouwuche.url.UrlView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UrlView {

    private ExpandableListView mElView;
    private CheckBox mBox;
    private String path = "http://120.27.23.105/product/getCarts?source=android&uid=99";
    private TextView mHeji;
    private UrlPresenter mUrlPresenter;
    private ExAdapter mExAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckAll();
            }
        });
    }

    private void initView() {
        mElView = findViewById(R.id.ElView);
        mBox = findViewById(R.id.box);
        mHeji = findViewById(R.id.heji);
        mUrlPresenter = new UrlPresenter(this);
        mUrlPresenter.Url(path);
    }

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        GoodsEntity goodsEntity = gson.fromJson(result, GoodsEntity.class);
        List<GoodsEntity.DataBean> data = goodsEntity.getData();

        mExAdapter = new ExAdapter(MainActivity.this, data);
        mElView.setAdapter(mExAdapter);
        for (int i = 0; i < mExAdapter.getGroupCount(); i++) {
         mElView.expandGroup(i);
        }
       initShopcartChange();
    }

    //父类点击选中
    private void initShopcartChange() {
        mElView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                GoodsEntity.DataBean group = (GoodsEntity.DataBean) mExAdapter.getGroup(groupPosition);
                //让group中的checkbox选中,子类就跟着选中
                group.setIschecked(!group.isIschecked());
                boolean flag = true;
                if (group.isIschecked()) {
                    flag = false;
                }
                List<GoodsEntity.DataBean.ListBean> list = group.getList();
                for (int i = 0; i < list.size(); i++) {
                    GoodsEntity.DataBean.ListBean listBean = list.get(i);
                    listBean.setCheck(!flag);
                }
                mExAdapter.notifyDataSetChanged();
             //   getTotal();
                return true;
            }
        });
        //子类的合计
        mElView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                GoodsEntity.DataBean.ListBean child = (GoodsEntity.DataBean.ListBean) mExAdapter.getChild(groupPosition, childPosition);
                boolean check = child.isCheck();
                if (check) {
                    child.setCheck(true);
                } else {
                    child.setCheck(false);
                }
                mExAdapter.notifyDataSetChanged();
           //     getTotal();
                return true;
            }
        });
        //setOnNumChangeListener
        mExAdapter.setOnNunChangeListener(new Subview.onNunChangeListener() {
            @Override
            public void onNumChange(View view, int curNum) {
                getTotal();
            }
        });
    }
    //=================价格总计===================
    private void getTotal() {
        float total = 0;
        int groupCount = mExAdapter.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            GoodsEntity.DataBean group = (GoodsEntity.DataBean) mExAdapter.getGroup(i);
            List<GoodsEntity.DataBean.ListBean> list = group.getList();
            for (int j = 0; j < list.size(); j++) {
                GoodsEntity.DataBean.ListBean listBean = list.get(j);
                boolean check = listBean.isCheck();
                if (check == true) {
                    double price = listBean.getPrice();
                    total = (float) (total + price * listBean.getNum());
                }
            }
        }
        mHeji.setText("合计:" + total);
    }
    //==================主界面全选================
    private void setCheckAll(){
        int groupCount = mExAdapter.getGroupCount();
        for (int i = 0; i <groupCount; i++) {
            GoodsEntity.DataBean group1 = (GoodsEntity.DataBean) mExAdapter.getGroup(i);
            List<GoodsEntity.DataBean.ListBean> list = group1.getList();
            for (int j = 0; j <list.size() ; j++) {
                GoodsEntity.DataBean.ListBean listBean = list.get(j);
                if (mBox.isChecked()){
                    group1.setIschecked(true);
                    listBean.setCheck(true);
                }
                else {
                    group1.setIschecked(false);
                    listBean.setCheck(false);
                }
            }
        }
        mExAdapter.notifyDataSetChanged();;
        getTotal();
    }
    @Override
    public void onFailer(String msg) {

    }
}
