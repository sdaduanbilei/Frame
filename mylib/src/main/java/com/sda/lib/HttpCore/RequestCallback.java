package com.sda.lib.HttpCore;


import okhttp3.Headers;

/**
 * Created by scorpio on 16/3/16.
 */
public class RequestCallback<T> {
    protected void onSuccess(Headers headers, T t) {

    }

    public void onFailure(int errorCode, String msg) {
    }
}
