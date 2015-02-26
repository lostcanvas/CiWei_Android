package com.ciwei.client.utils;

import android.content.Context;
import android.os.Environment;

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
    public static boolean sdcardReady(Context ctx){
        String state = Environment.getExternalStorageState ();
        if (!Environment.MEDIA_MOUNTED.equals (state)) { return false; }
        return true;
    }
}
