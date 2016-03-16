package com.sda.lib.HttpCore;

/**
 * Created by scorpio on 15/10/15.
 */
public interface DataResponse {

    public void onSucc(Object response);

    public void onFail(String error);
}
