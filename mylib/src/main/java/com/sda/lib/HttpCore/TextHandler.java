package com.sda.lib.HttpCore;

/**
 * Created by scorpio on 15/10/15.
 */
public interface TextHandler {

     public void onSuccess(int header, Object response);

     void onFailure(Throwable error);
}
