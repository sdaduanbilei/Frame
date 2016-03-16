package com.sda.lib.HttpCore;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.sda.lib.util.Logs;
import com.sda.lib.util.Tools;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by scorpio on 15/10/15.
 */
public class MyHttp {
    public static OkHttpClient client;
    public static String BASE_URL;
    public static int cache_size = 10; // 默认缓存空间 10M
    public static int connectTimeout = 15; // 默认超时时间 15 s
    public static Requests request;


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
        client = new OkHttpClient();
        client.setFollowSslRedirects(true);
        client.setConnectTimeout(connectTimeout, TimeUnit.SECONDS);
        client.setCookieHandler(new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL));
        try {
            long httpCacheSize = cache_size * 1024 * 1024;// 10M
            File httpCacheDir = new File(context.getCacheDir(), "http");
            Class.forName("android.net.http.HttpResponseCache")
                    .getMethod("install", File.class, long.class)
                    .invoke(null, httpCacheDir, httpCacheSize);
            Cache cache = new Cache(httpCacheDir, httpCacheSize);
            client.setCache(cache);
        } catch (Exception e) {
        }

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
            FormEncodingBuilder form_params = new FormEncodingBuilder();
            for (Map.Entry<String, String> entry : req.getParams().mStringParams.entrySet()) {
                String key = (URLEncoder.encode(entry.getKey(), req.getParams().UTF8));
                String value = (URLEncoder.encode(entry.getValue(), req.getParams().UTF8));
                form_params.add(key, value);
            }
            builder.url(req.getUrl());
            builder.post(form_params.build());
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
     * RxJAVA 事件绑定
     *
     * @param url_
     * @return
     */
    public static Observable<ResponseData> obs(RequestParams params, String url_, boolean refresh, final Method m) {
        request = new Requests();
        request.setParams(params);
        request.setUrl(getAbsoluteUrl(url_));
        request.setRefresh(refresh);

        return Observable.create(new Observable.OnSubscribe<ResponseData>() {
            @Override
            public void call(Subscriber<? super ResponseData> subscriber) {
                try {
                    subscriber.onNext(HttpCore(request, m));
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onCompleted();
                }

            }
        }).subscribeOn(Schedulers.io());
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
        obs(request, url, true, Method.POST).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.Debug("error==="+e.toString());
                    }

                    @Override
                    public void onNext(ResponseData o) {
                        if (o.getThrowable() != null) {
                            handler.onFailure(o.getThrowable());
                        } else {
                            handler.onSuccess(o.getHeader(), o.getData());
                        }
                    }
                });
    }


    public static void get(Context context, String url, RequestParams request, final TextHandler handler) {
        obs(request, url, true, Method.GET).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.Debug("error==="+e.toString());
                    }

                    @Override
                    public void onNext(ResponseData o) {
                        if (o.getThrowable() != null) {
                            handler.onFailure(o.getThrowable());
                        } else {
                            handler.onSuccess(o.getHeader(), o.getData());
                        }
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
