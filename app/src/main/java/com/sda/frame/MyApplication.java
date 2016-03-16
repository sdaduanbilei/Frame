package com.sda.frame;

import android.app.Application;


import com.sda.lib.HttpCore.MyHttp;
import com.sda.lib.util.AsyncImage;


/**
 * Created by scorpio on 16/3/2.
 */
public class MyApplication extends Application {

//    String BASE_URL = "https://cnbeta1.com/api/";
    String BASE_URL = "http://m.ch999.com/app/1_0/ProductSearch.aspx";
//    http://m.ch999.com/app/1_0/ProductSearch.aspx?act=GetVersion
    public static MyHttp http ;
    public static DataControl pdc ;

    @Override
    public void onCreate() {
        super.onCreate();
        http = new MyHttp();
        http.init(getApplicationContext(), BASE_URL);
        pdc = new DataControl(http);
        AsyncImage.init(getApplicationContext());

        //
//        OkHttpFinalConfiguration.Builder  builder= new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
    }
}
