package com.baway.guo.shopcar.Model;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.baway.guo.shopcar.App;
import com.baway.guo.shopcar.been.GoodsEntity;
import com.baway.guo.shopcar.OkCallBack;
import com.baway.guo.shopcar.OkUrlTppls;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UrlModel {

    public void Url(String url, final OkCallBack okCallBack) {
        OkUrlTppls.enqueueGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(App.context, "拜拜", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        GoodsEntity goodsEntity = gson.fromJson(s, GoodsEntity.class);
                        List<GoodsEntity.DataBean> data = goodsEntity.getData();
                        okCallBack.onSuccessResult(data);
                    }
                });
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


}
