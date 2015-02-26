package com.ciwei.client.ui;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ciwei.client.R;
/**
 * 闪屏启动页面
 */
public class SplashActivity extends BaseActivity {

    private final static String TAG = SplashActivity.class.getSimpleName ();
    // private ImageLoader imageLoader;
    // private DisplayImageOptions options;
    private Handler             handler;
    private ImageView           imageView;
    private TextView            descTextView;

    // private PushInfo mPushInfo;

    @Override
    protected int getLayoutResource(){
        return R.layout.acitivity_splash;
    }

    @Override
    protected void initView(){
        // MobclickAgent.updateOnlineConfig (this);
        handler = new Handler ();
        // imageView = (ImageView) findViewById (R.id.splash_img);
        // descTextView = (TextView) findViewById (splash_desc_tx);
        goToActivity();
    }

    @Override
    protected void setListener(){

    }

    @Override
    protected void progressLogic(){
        getXMPush ();
        initImageLoader ();
        getImageURL ();

    }

    @Override
    protected void onResume(){
        super.onResume ();
//        MobclickAgent.onResume (this);
    }

    @Override
    protected void onPause(){
        super.onPause ();
//        MobclickAgent.onPause (this);
    }

    @Override
    protected void onClickEvent(View v){

    }

    /**
     * 初始化图片加载工具
     */
    private void initImageLoader(){
//        imageLoader = ImageLoader.getInstance ();
//        options = new DisplayImageOptions.Builder ().showImageForEmptyUri (R.drawable.ic_screen_default)
//                .showImageOnFail (R.drawable.ic_screen_default)
//                .cacheOnDisk (false)
//                .cacheInMemory (false)
//                .imageScaleType (ImageScaleType.EXACTLY)
//                .bitmapConfig (Bitmap.Config.RGB_565)
//                .considerExifParams (true)
//                .build ();
    }

    /**
     * 获取推送
     */
    private void getXMPush(){
//        Intent i = getIntent ();
//        if (i != null && i.getSerializableExtra (PushMessageHelper.KEY_MESSAGE) != null) {
//            MiPushMessage message = (MiPushMessage) i.getSerializableExtra (PushMessageHelper.KEY_MESSAGE);
//            if (message != null) {
//                String customContent = message.getContent ();
//                if (customContent != null && customContent.length () != 0) {
//                    try {
//                        mPushInfo = JSON.parseObject (customContent, PushInfo.class);
//                    } catch (Exception e) {
//                        e.printStackTrace ();
//                    }
//
//                }
//            }
//        }
    }

    /**
     * 获取图片链接
     */
    private void getImageURL(){
//        String fileName = SharedPreferencesUtils.getString (AppConfig.SP_SPLASH_IMG_URL);
//        Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator ();
//        if (!fileName.equals ("")) {
//            File f = new File (AppConfig.CACHE_PATH_PIC + md5FileNameGenerator.generate (fileName));
//            if (f.exists ()) {
//                String desc = SharedPreferencesUtils.getString (AppConfig.SP_SPLASH_IMG_DESC);
//                descTextView.setText (desc);
//                loadImage ("file://" + AppConfig.CACHE_PATH_PIC + md5FileNameGenerator.generate (fileName));
//            } else {
//                loadImage ("drawable://" + R.drawable.ic_screen_default);
//
//            }
//        } else {
//            loadImage ("drawable://" + R.drawable.ic_screen_default);
//        }
    }

    /**
     * 获取图片
     *
     * @param url
     */
    private void loadImage(final String url){
//        imageLoader = ImageLoader.getInstance ();
//        imageLoader.displayImage (url, imageView, options, new ImageLoadingListener () {
//
//            @Override
//            public void onLoadingStarted(String arg0,View arg1){}
//
//            @Override
//            public void onLoadingFailed(String arg0,View arg1,FailReason arg2){
//                goToActivity ();
//            }
//
//            @Override
//            public void onLoadingComplete(String arg0,View arg1,Bitmap arg2){
//                // imageView1.startAnimation (alphaAnimation);
//                imageView.startAnimation (AnimationUtils.loadAnimation (SplashActivity.this, R.anim.splash_anim));
//                goToActivity ();
//            }
//
//            @Override
//            public void onLoadingCancelled(String arg0,View arg1){
//                goToActivity ();
//            }
//        });
    }

//    /**
//     * 是否是新版本
//     *
//     * @return
//     */
//    private boolean isNewVersion(){
//        int old_version_code = SharedPreferencesUtils.getInt (AppConfig.SP_VERSION_CODE);
//        int new_version_code = AndroidUtils.getVersionCode (this);
//        if (old_version_code < new_version_code) {
//            // 新版本
//            SharedPreferencesUtils.putInt (AppConfig.SP_VERSION_CODE, new_version_code);
//            SharedPreferencesUtils.putBoolean (AppConfig.SP_SWITCH_OFF_LINE, false);
//            SharedPreferencesUtils.putBoolean (AppConfig.SP_NEW_VERSION, true);
//            return true;
//        } else {
//            // 老版本
//            SharedPreferencesUtils.putBoolean (AppConfig.SP_NEW_VERSION, false);
//            return false;
//        }
//    }

    /**
     * 跳转
     */
    private void goToActivity(){
        handler.postDelayed (new Runnable () {
            @Override
            public void run(){
                Intent i = new Intent ();
//                if (isNewVersion ()) {
//                    i.setClass (SplashActivity.this, GuideActivity.class);

//                } else {
                    i.setClass (SplashActivity.this,UnlockGesturePasswordActivity.class);
//                    LogUtil.d (TAG, JSON.toJSONString (mPushInfo));
//                    if (mPushInfo != null) {
//                        Bundle bundle = new Bundle ();
//                        bundle.putSerializable ("pushInfo", mPushInfo);
//                        i.putExtras (bundle);
//                        LogUtil.d (TAG, JSON.toJSONString (mPushInfo));
//                    }
//
//                }

                startActivity (i);
                overridePendingTransition (R.anim.fade_in, R.anim.hold);
                finish ();
            }
        }, 2500);
    }

}
