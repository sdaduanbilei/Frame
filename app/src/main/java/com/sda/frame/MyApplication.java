package com.sda.frame;

import android.app.Application;


import com.sda.lib.HttpCore.MyHttp;
import com.sda.lib.util.AsyncImage;


/**
 * Created by scorpio on 16/3/2.
 */
public class MyApplication extends Application {

    String BASE_URL = "http://m.ch999.com/app/1_0/ProductSearch.aspx";
    public static MyHttp http ;
    public static DataControl pdc ;

    @Override
    public void onCreate() {
        super.onCreate();
        MyHttp.init(getApplicationContext(), BASE_URL);
        pdc = new DataControl(getApplicationContext());
        AsyncImage.init(getApplicationContext());

        //
//        OkHttpFinalConfiguration.Builder  builder= new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
    }
}
