package com.baway.guo.shopcar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.baway.guo.shopcar.View.UrlView;
import com.baway.guo.shopcar.adapter.RecycleAdapter;
import com.baway.guo.shopcar.been.GoodsEntity;
import com.baway.guo.shopcar.persenter.UrlPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UrlView {
    private String path = "http://120.27.23.105/product/getCarts?source=android&uid=99";
    private ExpandableListView mElCart;
    private CheckBox mBox;
    /**
     * 合计:$0.00
     */
    private TextView mPrice;
    /**
     * 去结算
     */
    private Button mPay;
    private UrlPresenter mUrlPresenter;
    private RecycleAdapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        mElCart = findViewById(R.id.el_cart);
        mBox = findViewById(R.id.box);
        mPrice = findViewById(R.id.price);
        mPay = findViewById(R.id.pay);
        mPay.setOnClickListener(this);
    }

    private void initData() {
        mUrlPresenter = new UrlPresenter(this);
        mUrlPresenter.getUrl(path);
        mBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckAll();
            }

            private void setCheckAll() {


            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pay:
                break;
        }
    }

    @Override
    public void onSuccess(List<GoodsEntity.DataBean> result) {
        mRecycleAdapter = new RecycleAdapter(MainActivity.this,result);
        mElCart.setAdapter(mRecycleAdapter);
        for (int i = 0; i < mRecycleAdapter.getGroupCount(); i++) {
            mElCart.expandGroup(i);
        }
        initShopcartChange();
    }

    private void initShopcartChange() {
        mElCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                GoodsEntity.DataBean group = (GoodsEntity.DataBean) mRecycleAdapter.getGroup(groupPosition);
                //  //让group中的checkbox选中,之类就跟着选中
                group.setChecked(!group.isChecked());
                boolean flag = true;
                if (group.isChecked()) {
                    flag = false;
                }
                List<GoodsEntity.DataBean.ListBean> list = group.getList();
                for (int i = 0; i < list.size(); i++) {
                    GoodsEntity.DataBean.ListBean listBean = list.get(i);
                    listBean.setCheck(!flag);
                }
                getTotal();
                mRecycleAdapter.notifyDataSetInvalidated();
                ;

                return true;
            }

            //==============价格总计===============
            private void getTotal() {
                float total = 0;
                int groupCount = mRecycleAdapter.getGroupCount();
                for (int i = 0; i < groupCount; i++) {
                    GoodsEntity.DataBean group = (GoodsEntity.DataBean) mRecycleAdapter.getGroup(i);
                    List<GoodsEntity.DataBean.ListBean> list = group.getList();
                    for (int j = 0; j < list.size(); j++) {
                        GoodsEntity.DataBean.ListBean listBean = list.get(j);
                        boolean check = listBean.isCheck();
                        if (check == true) {
                            int price = listBean.getPrice();
                            total = total + price * listBean.getNum();
                        }
                    }
                }
                mPrice.setText("合计:" + total);
            }
        });
        //子类的合计
        mElCart.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                GoodsEntity.DataBean child = (GoodsEntity.DataBean) mRecycleAdapter.getChild(groupPosition, childPosition);
                boolean check = child.isChecked();
                if (check) {
                   child.setChecked(true);
                } else {
                    child.setChecked(false);
                }
                mRecycleAdapter.notifyDataSetChanged();

                return true;
            }
        });

    }

    @Override
    public void onFalier(String msg) {

    }
}
