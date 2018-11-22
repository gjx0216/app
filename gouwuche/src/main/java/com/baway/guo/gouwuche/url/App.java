package com.baway.guo.gouwuche.url;

import android.app.Application;
import android.content.Context;

import com.baway.guo.gouwuche.url.Urltools;

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Urltools.init();
        ;
    }
}
