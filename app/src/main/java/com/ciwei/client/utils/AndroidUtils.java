package com.ciwei.client.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.ciwei.client.R;
import com.ciwei.client.ui.activity.SplashActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vernon on 15/2/11.
 */
public class AndroidUtils {

    /**
     * 判断SD卡是否存在
     *
     * @param ctx
     * @return
     */
    public static boolean sdcardReady(Context ctx) {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return false;
        }
        return true;
    }

    /**
     * 判断设备当前是否在线
     *
     * @param ctx
     * @return
     */
    public static boolean isOnline(Context ctx) {
        boolean flag = false;
        try {
            ConnectivityManager cwjManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = cwjManager.getActiveNetworkInfo();
            if (null == networkinfo || !networkinfo.isAvailable()) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (Exception e) {
            flag = true;
        }
        return flag;
    }

    /**
     * 启动Activity
     *
     * @param activity
     * @param intent
     */
    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * 启动Activity返回参数
     *
     * @param activity
     * @param intent
     */
    public static void startActivityForResult(Activity activity, Intent intent, int i) {
        activity.startActivityForResult(intent, i);
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * 父容器启动Activity
     *
     * @param activity
     * @param intent
     */
    public static void startByParentActivity(Activity activity, Intent intent) {
        activity.getParent().startActivity(intent);
        activity.getParent().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * 结束当前Activity
     *
     * @param activity
     */
    public static void activityFinish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获取版本Code
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int code = 0;
        // 获取PackageManager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            code = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
}
