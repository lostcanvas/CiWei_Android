package com.ciwei.client.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ciwei.client.common.AppConfig;
import com.ciwei.client.model.UserBean;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.LockPatternUtils;
import com.ciwei.client.utils.LogUtil;
import com.ciwei.client.utils.ScreenListener;
import com.ciwei.client.utils.SharedPreferencesUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vernon on 15/2/11.
 */
public class GlobalApp extends Application {

    public static String     TAG     = GlobalApp.class.getSimpleName (); // TAG

    private static GlobalApp mInstance;
    // 运用list来保存们每一个activity是关键
    private List<Activity>   mList   = new LinkedList<Activity> ();
    private LockPatternUtils mLockPatternUtils;

    /** 程序在前台 */
    public static boolean isActive = false;
    /** 程序从后台到前台 */
    public static boolean isBtoF = false;
    /** 正在显示lock界面 */
    public static boolean isShowLock = false;
    /** 不显示显示lock界面【splash界面和login界面】 */
    public static boolean notShowLock = false;
    /** 显示亮屏的次数 */
    public static int ligthCount = 0;
    /** main界面resume次数 */
    public static int mainCount = 0;
    /** 屏幕锁屏监听 */
    public static ScreenListener sl;
    public static Context    mContext;

    public static GlobalApp getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate ();
        mInstance = this;
        mContext = getApplicationContext ();
        mLockPatternUtils = new LockPatternUtils (this);
        // 图片加载框架
        initImageLoader (this);
        initSharedPreferencesUtil ();
        initFileDir ();
    }

    private void initImageLoader(Context context){
        ImageLoaderConfiguration config = null;
        File picFile = new File (AppConfig.CACHE_PATH_PIC);
        if (AndroidUtils.sdcardReady (mContext) && picFile != null) {
            UnlimitedDiscCache disCache = new UnlimitedDiscCache (picFile);
            config = new ImageLoaderConfiguration.Builder (context).diskCache (disCache)
                    .denyCacheImageMultipleSizesInMemory ()
                    .diskCacheFileNameGenerator (new HashCodeFileNameGenerator ())
                    .tasksProcessingOrder (QueueProcessingType.LIFO)
                    // Not
                    .memoryCache (new WeakMemoryCache ())
                    .threadPriority (Thread.MAX_PRIORITY)
                    .threadPoolSize (3)
                    .diskCacheExtraOptions (480, 800, null)
                    .build ();
        } else {

            config = new ImageLoaderConfiguration.Builder (context).threadPriority (Thread.MAX_PRIORITY)
                    .denyCacheImageMultipleSizesInMemory ()
                    .diskCacheFileNameGenerator (new HashCodeFileNameGenerator ())
                    .tasksProcessingOrder (QueueProcessingType.LIFO)
                    // Not
                    .memoryCache (new WeakMemoryCache ())
                    .threadPoolSize (3)
                    .diskCacheExtraOptions (320, 480, null)
                    .build ();
        }
        ImageLoader.getInstance ().init (config);
        LogUtil.d (TAG, "图片框架初始化完毕");
    }

    public void addActivity(Activity activity){
        mList.add (activity);
    }

    public void exit(){
        for ( Activity activity : mList ) {
            if (activity != null) activity.finish ();
        }
    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     */
    public static void saveUserInfo(UserBean userInfo){
        SharedPreferencesUtils.putString (AppConfig.SP_USER_INFO, JSON.toJSONString (userInfo));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUserInfo(){
        String str = SharedPreferencesUtils.getString (AppConfig.SP_USER_INFO);
        if (str.equals ("")) {
            UserBean userInfo = new UserBean ();
            userInfo.setUid ("");
            userInfo.setToken ("");
            return userInfo;
        } else {
            return JSON.parseObject (str, UserBean.class);
        }
    }

    /**
     * 判断是否登陆
     *
     * @return
     */
    public static boolean isLogin(){
        boolean flag = false;
        if (getUserInfo ().getToken ().equals ("")) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 清除用户信息
     */
    public static void clearUserInfo(){
        SharedPreferencesUtils.getEditor ().remove (AppConfig.SP_USER_INFO).commit ();
    }
    /**
     * 初始化存储工具
     */
    private void initSharedPreferencesUtil(){
        SharedPreferencesUtils.initConfigFile (this);
    }
    /**
     * 初始化缓存文件夹
     */
    public void initFileDir(){
        File cacheHome = new File (AppConfig.CACHE_PATH_HOME);
        File cacheDirFile = new File (AppConfig.CACHE_PATH_FILE);
        File doloadFile = new File (AppConfig.CACHE_PATH_MP3);
        File picFile = new File (AppConfig.CACHE_PATH_PIC);
        File downloadPicFile = new File (AppConfig.CACHE_PATH_DOWNLOAD_PIC);
        if (!cacheHome.exists ()) {
            cacheHome.mkdirs ();
        }
        if (!cacheDirFile.exists ()) {
            cacheDirFile.mkdirs ();
        }
        if (!doloadFile.exists ()) {
            doloadFile.mkdirs ();
        }
        if (!picFile.exists ()) {
            picFile.mkdirs ();
        }
        if (!downloadPicFile.exists ()) {
            downloadPicFile.mkdirs ();
        }

    }

    public LockPatternUtils getLockPatternUtils(){
        return mLockPatternUtils;
    }
}
