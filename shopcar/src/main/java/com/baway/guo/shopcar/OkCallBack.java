package com.baway.guo.shopcar;

import com.baway.guo.shopcar.been.GoodsEntity;

import java.util.List;

public interface OkCallBack {

    void onSuccessResult(List<GoodsEntity.DataBean> result);

    void onFailerResult(String mag);
}
