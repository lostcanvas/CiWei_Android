package com.ciwei.client.common;

import android.os.Environment;

/**
 * Created by Vernon on 15/2/11.
 */
public class AppConfig {
    /**
     * SD卡参数配置
     */
    // 根目录
    public static final String CACHE_PATH_HOME         = Environment.getExternalStorageDirectory() + "/" + "ciwei";
    // 文件下载地址
    public static final String CACHE_PATH_FILE         = CACHE_PATH_HOME + "/file/";
    // MP3下载地址
    public static final String CACHE_PATH_MP3          = CACHE_PATH_FILE + "mp3/";
    // 图片缓存地址
    public static final String CACHE_PATH_PIC          = CACHE_PATH_HOME + "/pic/";
}
