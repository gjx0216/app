package com.baway.guo.shopcar;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkUrlTppls {

    private static Gson gson = new Gson();
    private static final String METHOD_GET = "GET";
    private static OkHttpClient okHttpClient;


    private OkUrlTppls() {

    }

    public static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        okHttpClient = builder.build();
    }

    private static Request createRequest(String url, String method) {
        Request.Builder builder = new Request.Builder().url(url);

        Request request = null;

        switch (method) {
            case METHOD_GET:
                request = builder.build();
                break;
        }
        return request;
    }

    public static void enqueueGet(String url, Callback callback) {
        Request request = createRequest(url, METHOD_GET);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
