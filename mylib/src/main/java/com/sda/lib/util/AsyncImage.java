package com.sda.lib.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by scorpio on 15/12/22.
 */
public class AsyncImage {


    private static Context context;

    public static void init(Context c){
        context = c ;
    }

    public static void display(ImageView img,String url){
        Glide.with(context).load(url).into(img);
    }

    public static void display(ImageView img,Uri uri){
        Glide.with(context).load(uri).into(img);
    }

}
