package com.baway.guo.guo01.pathUrl;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.baway.guo.guo01.App;
import com.baway.guo.guo01.Ok.OkPathTools;
import com.baway.guo.guo01.Ok.OkUrlTools;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PathModel {

    public void Url(String path, final HttpData httpData) {
        OkPathTools.QueueGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(App.context, "失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    ResponseBody body = response.body();
                    final String s = body.string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpData.getUrl(s);
                        }
                    });
                }
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public interface HttpData {
        void getUrl(String s);
    }
}
