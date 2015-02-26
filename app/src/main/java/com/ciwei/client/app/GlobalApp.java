package com.ciwei.client.app;

import android.app.Application;
import android.content.Context;

import com.ciwei.client.common.AppConfig;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.LockPatternUtils;
import com.ciwei.client.utils.LogUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * Created by Vernon on 15/2/11.
 */
public class GlobalApp extends Application {

    public static String TAG = GlobalApp.class.getSimpleName(); // TAG

    private static GlobalApp mInstance;

    private LockPatternUtils mLockPatternUtils;

    public static Context mContext;


    public static GlobalApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        mLockPatternUtils = new LockPatternUtils(this);
        // 图片加载框架
        initImageLoader(this);
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration config = null;
        File picFile = new File(AppConfig.CACHE_PATH_PIC);
        if (AndroidUtils.sdcardReady(mContext) && picFile != null) {
            UnlimitedDiscCache disCache = new UnlimitedDiscCache(picFile);
            config = new ImageLoaderConfiguration.Builder(context).diskCache(disCache)
                    .denyCacheImageMultipleSizesInMemory()
                    .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                            // Not
                    .memoryCache(new WeakMemoryCache())
                    .threadPriority(Thread.MAX_PRIORITY)
                    .threadPoolSize(3)
                    .diskCacheExtraOptions(480, 800, null)
                    .build();
        } else {
            
            config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.MAX_PRIORITY)
                    .denyCacheImageMultipleSizesInMemory()
                    .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                            // Not
                    .memoryCache(new WeakMemoryCache())
                    .threadPoolSize(3)
                    .diskCacheExtraOptions(320, 480, null)
                    .build();
        }
        ImageLoader.getInstance().init(config);
        LogUtil.d(TAG, "图片框架初始化完毕");
    }

    public LockPatternUtils getLockPatternUtils() {
        return mLockPatternUtils;
    }
}
