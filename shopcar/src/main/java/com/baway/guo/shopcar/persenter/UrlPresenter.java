package com.baway.guo.shopcar.persenter;

import com.baway.guo.shopcar.Model.UrlModel;
import com.baway.guo.shopcar.OkCallBack;
import com.baway.guo.shopcar.View.UrlView;
import com.baway.guo.shopcar.been.GoodsEntity;

import java.util.List;

public class UrlPresenter {

    private UrlView mUrlView;
    private final UrlModel mUrlModel;

    public UrlPresenter(UrlView urlView) {
        this.mUrlView = urlView;
        mUrlModel = new UrlModel();
    }

    public void getUrl(final String url) {
        mUrlModel.Url(url, new OkCallBack() {
            @Override
            public void onSuccessResult(List<GoodsEntity.DataBean> result) {
                mUrlView.onSuccess(result);
            }

            @Override
            public void onFailerResult(String mag) {
                mUrlView.onFalier("失败");
            }
        });
    }
}
