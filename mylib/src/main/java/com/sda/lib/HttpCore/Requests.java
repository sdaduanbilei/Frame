package com.sda.lib.HttpCore;

/**
 * Created by scorpio on 15/10/27.
 */
public class Requests {

    private static RequestParams params ;
    private static String url ;
    private static boolean refresh ;

    public static RequestParams getParams() {
        return params;
    }

    public static void setParams(RequestParams params) {
        Requests.params = params;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Requests.url = url;
    }

    public static boolean isRefresh() {
        return refresh;
    }

    public static void setRefresh(boolean refresh) {
        Requests.refresh = refresh;
    }
}
