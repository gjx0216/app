package com.baway.guo.guo01.Ok;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkPathTools {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static OkHttpClient okHttpClient;


    private OkPathTools() {

    }

    public static void init() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static Request createRequest(String path, String method) {
        Request.Builder builder = new Request.Builder().url(path);
        Request request = null;
        switch (method) {
            case METHOD_GET:
                request = builder.build();
                break;
        }
        return request;
    }

    public static void QueueGet(String path, Callback callback) {
        Request mRequest = createRequest(path, METHOD_GET);
        Call call = okHttpClient.newCall(mRequest);
        call.enqueue(callback);


    }


}
