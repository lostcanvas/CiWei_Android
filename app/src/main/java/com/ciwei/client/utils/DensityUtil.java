package com.ciwei.client.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 单位换算工具
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context,float dpValue){
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context,float pxValue){
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转化成px
     * 
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue){
        final float fontScale = context.getResources ().getDisplayMetrics ().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     * 
     * @param context
     * @return
     */
    public static int screenWidth(Context context){
        DisplayMetrics dm = context.getResources ().getDisplayMetrics ();
        int w = dm.widthPixels;
        return w;
    }

    /**
     * 获取屏幕高度
     * 
     * @param context
     * @return
     */
    public static int screenHeight(Context context){
        DisplayMetrics dm = context.getResources ().getDisplayMetrics ();
        int h = dm.heightPixels;
        return h;
    }
}
