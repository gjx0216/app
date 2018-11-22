package com.baway.guo.guo01;

import android.app.Application;
import android.content.Context;

import com.baway.guo.guo01.Ok.OkPathTools;
import com.baway.guo.guo01.Ok.OkUrlTools;

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        OkUrlTools.init();
        OkPathTools.init();
        context = this;
    }
}
