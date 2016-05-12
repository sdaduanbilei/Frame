package com.sda.frame.other.request;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sda.frame.model.VersionData;
import com.sda.lib.HttpCore.DataResponse;
import com.sda.lib.HttpCore.MyHttp;
import com.sda.lib.HttpCore.RequestParams;
import com.sda.lib.HttpCore.TextHandler;
import com.sda.lib.util.Logs;


/**
 * Created by scorpio on 16/3/2.
 */
public class DataControl  {
    public DataControl(Context context) {
    }

    public  void getArticleList(Context context , final DataResponse dr){
        RequestParams params = new RequestParams();
        params.put("act","GetVersion");
        MyHttp.get(context, "", params, new TextHandler() {
            @Override
            public void onSuccess(int header, Object response) {
                Logs.Debug("list>>>>>>>>>>>>" + response.toString());
                JSONObject json = JSON.parseObject(response.toString());
                if (json.getInteger("stats") == 1) {
                    VersionData data = new Gson().fromJson(json.getJSONObject("data").toString(), VersionData.class);
                    dr.onSucc(data);
                }
            }

            @Override
            public void onFailure(Throwable error) {
                Logs.Debug("list error>>>>>>>" + error.toString());
            }
        });
    }


    public  void getArticleList2(Context context , final DataResponse dr){
        RequestParams params = new RequestParams();
        params.put("act","GetHome");
        params.put("cityid","530102");
        MyHttp.get(context,"",params, new TextHandler() {
            @Override
            public void onSuccess(int header, Object response) {
                Logs.Debug("list>>>>>>>>>>>>"+response.toString());
                JSONObject json = JSON.parseObject(response.toString());
//                if (json.getInteger("stats")==1){
//                    VersionData data = new Gson().fromJson(json.getJSONObject("data").toString(),VersionData.class);
//                    dr.onSucc(data);
//                }
                dr.onSucc(response);
            }

            @Override
            public void onFailure(Throwable error) {
                Logs.Debug("list error>>>>>>>"+error.toString());
            }
        });
    }


//    public static void getArticleList3(Context context){
//        cn.finalteam.okhttpfinal.RequestParams params = new cn.finalteam.okhttpfinal.RequestParams();
//        params.addFormDataPart("act", "GetHome");
//        params.addFormDataPart("cityid", "530102");
//        HttpRequest.get("http://m.9ji.com/app/1_0/ProductSearch.aspx",params,new BaseHttpRequestCallback(){
//            @Override
//            protected void onSuccess(Headers headers, Object o) {
//                super.onSuccess(headers, o);
//                Log.d("list3>>>>>>>>>>>>" , o.toString());
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//            }
//        });
//    }
//
//
//    public static void getArticleList4(Context context){
//        cn.finalteam.okhttpfinal.RequestParams params = new cn.finalteam.okhttpfinal.RequestParams();
//        params.addFormDataPart("act", "GetVersion");
//        HttpRequest.get("http://m.9ji.com/app/1_0/ProductSearch.aspx",params,new BaseHttpRequestCallback(){
//            @Override
//            protected void onSuccess(Headers headers, Object o) {
//                super.onSuccess(headers, o);
//                Log.d("list3>>>>>>>>>>>>" , o.toString());
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//            }
//        });
//    }
}
