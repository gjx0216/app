package com.baway.guo.shopcar.View;

import com.baway.guo.shopcar.been.GoodsEntity;

import java.util.List;

public interface UrlView {
    void onSuccess(List<GoodsEntity.DataBean> result);

    void onFalier(String msg);
}
