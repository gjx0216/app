package com.baway.guo.guo01.Ok;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkUrlTools {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static OkHttpClient okHttpClient;


    private OkUrlTools() {

    }

    public static void init() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static Request createRequest(String url, String method) {
        Request.Builder builder = new Request.Builder().url(url);
        Request request = null;
        switch (method) {
            case METHOD_GET:
                request = builder.build();
                break;
        }
        return request;
    }

    public static void QueueGet(String url, Callback callback) {
        Request mRequest = createRequest(url, METHOD_GET);
        Call call = okHttpClient.newCall(mRequest);
        call.enqueue(callback);


    }


}
