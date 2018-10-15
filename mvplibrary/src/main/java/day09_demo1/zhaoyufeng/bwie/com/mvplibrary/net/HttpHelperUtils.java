package day09_demo1.zhaoyufeng.bwie.com.mvplibrary.net;


import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public  class HttpHelperUtils {
    private HttpListener listener;
    private final int HTTP_SUCCESS=1000;
    private final int HTTP_FAILURE=1001;
    public HttpHelperUtils(HttpListener listener){
        this.listener=listener;
    }
    public interface HttpListener{
        void success(String data);
        void error();
    }
    public HttpHelperUtils  get(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(HTTP_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what=HTTP_SUCCESS;
                message.obj=string;
                handler.sendMessage(message);
            }
        });
        return this;
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case HTTP_SUCCESS:
                    String data = (String) msg.obj;
                    listener.success(data);
                    break;
                case HTTP_FAILURE:
                    listener.error();
                    break;
            }

        }
    };


}
