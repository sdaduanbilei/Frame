package com.sda.lib.HttpCore;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.sda.lib.util.Logs;
import com.sda.lib.util.Tools;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by scorpio on 15/10/15.
 */
public class MyHttp {
    public static OkHttpClient client;
    public static String BASE_URL;
    public static int cache_size = 10; // 默认缓存空间 10M
    public static int connectTimeout = 15; // 默认超时时间 15 s
    public static Requests request;
    public static Cache cache ;

    public enum Method {
        GET,
        POST
    }


    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * 初始化操作
     *
     * @param context
     * @param url
     */
    public static void init(Context context, String url) {
        try {
            long httpCacheSize = cache_size * 1024 * 1024;// 10M
            File httpCacheDir = new File(context.getCacheDir(), "http");
            Class.forName("android.net.http.HttpResponseCache")
                    .getMethod("install", File.class, long.class)
                    .invoke(null, httpCacheDir, httpCacheSize);
        } catch (Exception e) {
        }

        client = new OkHttpClient().newBuilder()
                .followRedirects(true)
                .followSslRedirects(true)
                .cache(cache)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS).build();
        BASE_URL = url;
    }


    /**
     * 请求核心代码
     *
     * @return
     * @throws IOException
     */
    private static ResponseData HttpCore(Requests req, Method method) throws IOException {
        ResponseData data = new ResponseData();
        Request.Builder builder = new Request.Builder();
        if (!TextUtils.isEmpty(req.getParams().getCookie())) {
            builder.addHeader("Cookie", req.getParams().getCookie());
        }
        if (req.getParams().isRefresh) {
            builder.addHeader("Cache-Control", "max-age=0");
        }
        if (method == Method.GET) {
            if (!Tools.isEmpty(req.getParams().toString())) {
                builder.url(req.getUrl() + "?" + req.getParams().toString());
            }else {
                builder.url(req.getUrl());
            }
        } else {
            FormBody.Builder formbody = new FormBody.Builder();
            for (Map.Entry<String, String> entry : req.getParams().mStringParams.entrySet()) {
                String key = (URLEncoder.encode(entry.getKey(), req.getParams().UTF8));
                String value = (URLEncoder.encode(entry.getValue(), req.getParams().UTF8));
                formbody.add(key, value);
            }
            builder.url(req.getUrl());
            builder.post(formbody.build());
        }
        Request request = builder.build();
        Logs.Debug("client request==" + request.toString());
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                data.setData(response.body().string() + "");
                data.setHeader(response.code());
                return data;
            } else {
                data.setThrowable(new Throwable("CODE =" + response.code() + response.toString()));
                return data;
            }
        } catch (Exception e) {
            data.setThrowable(new Throwable(e.toString()));
            return data;
        }

    }




    /**
     * post 请求方法
     *
     * @param context
     * @param request
     * @param url
     * @param handler
     */
    public static void post(Context context, String url, RequestParams request, final TextHandler handler) {
        executeRequest(Method.POST, context, getAbsoluteUrl(url), request, handler);
    }


    public static void get(Context context, String url, final RequestParams request, final TextHandler handler) {
        executeRequest(Method.GET, context, getAbsoluteUrl(url), request, handler);
    }

    /**
     * okhttp 实现请求
     * @param method
     * @param context
     * @param url
     * @param req
     * @param handler
     */
    private static void executeRequest(Method method, final Context context, String url, RequestParams req, final TextHandler handler) {
        ResponseData data = new ResponseData();
        Request.Builder builder = new Request.Builder();
        if (!TextUtils.isEmpty(req.getCookie())) {
            builder.addHeader("Cookie", req.getCookie());
        }
        if (req.isRefresh) {
            builder.addHeader("Cache-Control", "max-age=0");
        }
        if (method == Method.GET) {
            if (!Tools.isEmpty(req.toString())) {
                builder.url(url + "?" + req.toString());
            }else {
                builder.url(url);
            }
        } else {
            FormBody.Builder formbody = new FormBody.Builder();
            for (Map.Entry<String, String> entry : req.mStringParams.entrySet()) {
                String key = null;
                try {
                    key = (URLEncoder.encode(entry.getKey(), req.UTF8));
                    String value = (URLEncoder.encode(entry.getValue(), req.UTF8));
                    formbody.add(key, value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
            builder.url(url);
            builder.post(formbody.build());
        }
        Request request = builder.build();
        Logs.Debug("client request==" + request.toString());
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            Handler mainHandler = new Handler(context.getMainLooper());
            @Override
            public void onFailure(Call call, final IOException e) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        handler.onFailure(new Throwable(e));
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String body = response.body().string();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        handler.onSuccess(response.code(), body);
                    }
                });

            }
        });
    }





    /**
     * 获取请求链接
     *
     * @param relativeUrl
     * @return
     */
    public static String getAbsoluteUrl(String relativeUrl) {
        if (relativeUrl.startsWith("http")) {
            return relativeUrl;
        } else {
            return BASE_URL + relativeUrl;
        }
    }
}
