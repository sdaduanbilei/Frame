package com.sda.lib.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scorpio on 15/10/16.
 */
public class Tools {

    /**
     * 显示对话框
     *
     * @param context
     * @param msg
     */
    public static void msgbox(Context context, String msg) {
        new AlertDialog.Builder((context)).setTitle("提示").setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }

    /**
     * 获取手机的IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String deviceId = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();
        return deviceId;
    }

    /**
     * 判断null和""的情况
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            if (str.length() > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * toast 显示提示信息
     *
     * @param context
     * @param s
     */
    public static void toast(Context context, String s) {
        if (!Tools.isEmpty(s)) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        }
    }

    public static String currentVersion(Context context) {
        String ver = null;
        try {
            ver = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ver;
    }

    /**
     * MD5 加密
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }


    /**
     * 初始化申请权限 6.0
     */
    public static void requestPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) !=
                PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
//                // 如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
//            } else {
                // 进行权限请求
                ActivityCompat.requestPermissions(activity, new String[]{permission}, 10);
//            }
        }
    }

    /**
     * 初始化申请权限 6.0 批量权限申请
     *
     * @param activity
     * @param permissions
     */
    public static void requestPermissions(final Activity activity, String[] permissions) {
        // 进行权限请求
        ActivityCompat.requestPermissions(activity, permissions, 10);
        int count = 0 ;
        for (int i = 0; i < permissions.length; i++) {
            final String permission = permissions[i];
            if (ContextCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermission(activity, permission);
            }

        }

    }


    /**
     * 权限 6.0 检测权限
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkoutPermissions(final Context context, String[] permissions) {
        // 进行权限请求
        boolean result = true;
        int count = 0;
        for (int i = 0; i < permissions.length; i++) {
            final String permission = permissions[i];
            if (ActivityCompat.checkSelfPermission(context, permission)==PackageManager.PERMISSION_GRANTED) {
                count = count + 1;
            }
        }

        if (count == permissions.length) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 显示金额默认保留两位小数
     */
    public static String formatMoney (double money) {
        String result = String.format("%.2f", money);
        return result;
    }

    /**
     * 规范时间格式
     */
    public static String formateTime(String time) {

        String[] t = time.split("\\(|\\)");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return sdf.format(Long.parseLong(t[1]));
    }

    /**
     * 规范日期
     * @param date
     * @return
     */
    public static String formateDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);//定位时间
    }

    /**
     * 规范服务器获取的日期
     * @param date
     * @return
     */
    public static String formateGetDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);//定位时间
    }

    /**
     * 判断String 是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    /**
     * 通过utf8 encode
     *
     * @param str
     * @return
     */
    public static String encodeUrlString(String str) {
        String s = str;
        try {
            s = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

}
