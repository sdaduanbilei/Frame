package com.sda.frame.model;

/**
 * Created by scorpio on 16/3/15.
 */
public class VersionData {

    /**
     * isforced : 1
     * version : 30
     * name : ch999App
     * url : http://www.ch999.com/topic/Android/ch999.apk
     * dsc : 版本1.3.4，添加功能：
     1、三九周年庆活动即将上线！3月7日-3月13日为“机友”们带来超有趣的积分大转盘和满载好礼！
     2、修复部分bug，使用更加流畅！
     3、首页广告版块重塑！

     */

    private int isforced;
    private int version;
    private String name;
    private String url;
    private String dsc;

    public void setIsforced(int isforced) {
        this.isforced = isforced;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public int getIsforced() {
        return isforced;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDsc() {
        return dsc;
    }
}
