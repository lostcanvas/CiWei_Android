package com.ciwei.client.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Context;

import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.LogUtil;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtil {

    private static final String    TAG    = HttpUtil.class.getName (); // TAG
    private static AsyncHttpClient client = null;

    /**
     * 获取请求实例
     *
     * @return
     */
    private static AsyncHttpClient getAsyncHttpClient(){
        if (client == null) {
            client = new AsyncHttpClient ();
            client.setMaxConnections (30);
            client.setTimeout (60 * 1000);
            // 加入线程池
            client.setThreadPool (new ThreadPoolExecutor (20,30,50,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable> (50),new ThreadPoolExecutor.DiscardOldestPolicy ()));
        }
        return client;

    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        if (!AndroidUtils.isOnline (GlobalApp.mContext)) {
            ToastUtil.showToast (GlobalApp.mContext, "网络不给力，请检查网络环境");
            return;
        }
        // if (GlobalApp.isLogin ()) {
        // params.put ("auth", GlobalApp.getUserInfo ().getAuth ());
        // // WZZvESm228m35DCMF3G8LijilwTsuqMLKO9WhkUTMpbm1bw3dNETRhV6g
        // // params.put ("auth", "WZZvESm228m35DCMF3G8LijilwTsuqMLKO9WhkUTMpbm1bw3dNETRhV6g");
        // }
        params.put("os","android");
        params.put("version",GlobalApp.mContext.getResources().getString(R.string.version));
        getAsyncHttpClient ().post(GlobalApp.mContext, getUrl(url), params, responseHandler);

        LogUtil.i (TAG, "POST请求地址#####################" + getUrl (url));
        if (params != null) {
            LogUtil.i (TAG, "POST请求参数#####################" + params.toString ());
        }
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        params.put("os","android");
        params.put("version",GlobalApp.mContext.getResources().getString(R.string.version));
        getAsyncHttpClient ().get (getUrl (url), params, responseHandler);
        LogUtil.i (TAG, "GET请求地址#####################" + getUrl (url));
        if (params != null) {
            LogUtil.i (TAG, "GET请求参数#####################" + params.toString ());
        }
    }

    /**
     * 取消请求
     * 
     * @param mContext
     */
    public static void cancelRequest(Context mContext){
        getAsyncHttpClient ().cancelRequests (mContext, true);
    }

    /**
     * 装饰Url
     *
     * @param url
     * @return
     */
    private static String getUrl(String url){
        return url;
    }

}
