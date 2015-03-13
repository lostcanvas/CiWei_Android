package com.ciwei.client.common;

import android.os.Environment;

/**
 * Created by Vernon on 15/2/11.
 */
public class AppConfig {

    /**
     * 项目配置
     */
    public static final String APP_NAME                = "ciwei";                                                   // 项目名称
    public static final String APP_PACKAGE_NAME        = "com.ciwei.client";

    /**
     * sharedPreferences配置
     */

    public static final String SP_USER_INFO            = APP_PACKAGE_NAME + ".userinfo";

    /**
     * SD卡参数配置
     */
    // 根目录
    public static final String CACHE_PATH_HOME         = Environment.getExternalStorageDirectory () + "/" + "ciwei";
    // 文件下载地址
    public static final String CACHE_PATH_FILE         = CACHE_PATH_HOME + "/file/";
    // MP3下载地址
    public static final String CACHE_PATH_MP3          = CACHE_PATH_FILE + "mp3/";
    // 图片缓存地址
    public static final String CACHE_PATH_PIC          = CACHE_PATH_HOME + "/pic/";
    public static final String CACHE_PATH_DOWNLOAD_PIC = CACHE_PATH_FILE + "ciweiImg/";

    // 数据库表名
    public static final String TABLE_BASE_DATA         = "basedata";
    // 绑定应用账户表
    public static final String TABLE_APP_ACCOUNT       = "account";
    // app列表
    public static final String TABLE_APPS              = "apps";
    public static final String SP_VERSION_CODE         = APP_PACKAGE_NAME + ".version.code";                         // 版本VersionCode
    public static final String SP_NEW_VERSION          = APP_PACKAGE_NAME + ".new.version";

}
