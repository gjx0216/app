package com.baway.guo.guo01.urlmvp;

public class UrlPresenter {

    private UrlView mUrlView;
    private final UrlModel mUrlModel;

    public UrlPresenter(UrlView urlView) {
        mUrlView = urlView;
        mUrlModel = new UrlModel();
    }


    public void getUrls(String url) {
        mUrlModel.Url(url, new UrlModel.HttpData() {
            @Override
            public void getUrl(String s) {
                if (s != null) {
                    mUrlView.onSuccess(s);
                } else {
                    mUrlView.onFalier("失败没网");
                }
            }
        });
    }
}
