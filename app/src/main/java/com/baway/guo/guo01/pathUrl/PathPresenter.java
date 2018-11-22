package com.baway.guo.guo01.pathUrl;

import com.baway.guo.guo01.urlmvp.UrlModel;
import com.baway.guo.guo01.urlmvp.UrlView;

public class PathPresenter {

    private PathView mPathView;
    private PathModel mPathModel;

    public PathPresenter(PathView urlView) {
        this.mPathView = urlView;
        mPathModel = new PathModel();
    }

    public void getUrls(String path) {
        mPathModel.Url(path, new PathModel.HttpData() {
            @Override
            public void getUrl(String s) {
                if (s != null) {
                    mPathView.onPathSuccess(s);
                } else {
                    mPathView.onPathFalier("失败");
                }
            }
        });
    }
}
