package com.ciwei.client.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.common.AppConfig;
import com.ciwei.client.db.AppAccountManager;
import com.ciwei.client.db.AppManager;
import com.ciwei.client.db.BaseDataManager;
import com.ciwei.client.http.HttpAPI;
import com.ciwei.client.http.HttpUtil;
import com.ciwei.client.model.Account;
import com.ciwei.client.model.App;
import com.ciwei.client.model.AppAccount;
import com.ciwei.client.model.BaseDataBean;
import com.ciwei.client.model.CommonBean;
import com.ciwei.client.model.FieldBean;
import com.ciwei.client.model.RegFieldBean;
import com.ciwei.client.model.ResultDataBean;
import com.ciwei.client.model.UserFieldBean;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.JSONUtil;
import com.ciwei.client.utils.LogUtil;
import com.ciwei.client.utils.SharedPreferencesUtils;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.umeng.analytics.MobclickAgent;

import org.apache.http.Header;

import java.util.List;

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
    private BaseDataManager     baseDataManager;
    private AppAccountManager   appAccountManager;
    private AppManager          appManager;

    // private PushInfo mPushInfo;

    @Override
    protected int getLayoutResource(){
        return R.layout.acitivity_splash;
    }

    @Override
    protected void initView(){
        MobclickAgent.updateOnlineConfig (this);
        GlobalApp.getInstance ().addActivity (SplashActivity.this);
        baseDataManager = BaseDataManager.getInstance (this);
        appAccountManager = AppAccountManager.getInstance (this);
        appManager = AppManager.getInstance (this);
        handler = new Handler ();
        goToActivity ();

    }
    /**
     * 进入登录界面
     */
    private void gotoLoginActivity(){
        Intent intent = new Intent (this,LoginActivity.class);
        AndroidUtils.startActivity (this, intent);
    }

    @Override
    protected void setListener(){
    }

    @Override
    protected void progressLogic(){
        getXMPush ();
        initImageLoader ();
        getImageURL ();
        getBaseDataFromServer ();
        getAccessDataFromServer ();
    }

    @Override
    protected void onResume(){
        super.onResume ();
        MobclickAgent.onResume (this);
        // splash界面不显示九宫格解锁
        GlobalApp.notShowLock = true;
    }

    @Override
    protected void onPause(){
        super.onPause ();
        MobclickAgent.onPause (this);
    }

    @Override
    protected void onClickEvent(View v){

    }

    /**
     * 注册界面
     */
    private void gotoRegisActivity(){
        Intent intent = new Intent (this,RegisActivity.class);
        AndroidUtils.startActivity (this, intent);
    }

    /**
     * 初始化图片加载工具
     */
    private void initImageLoader(){
        // imageLoader = ImageLoader.getInstance ();
        // options = new DisplayImageOptions.Builder ().showImageForEmptyUri (R.drawable.ic_screen_default)
        // .showImageOnFail (R.drawable.ic_screen_default)
        // .cacheOnDisk (false)
        // .cacheInMemory (false)
        // .imageScaleType (ImageScaleType.EXACTLY)
        // .bitmapConfig (Bitmap.Config.RGB_565)
        // .considerExifParams (true)
        // .build ();
    }

    /**
     * 获取推送
     */
    private void getXMPush(){
        // Intent i = getIntent ();
        // if (i != null && i.getSerializableExtra (PushMessageHelper.KEY_MESSAGE) != null) {
        // MiPushMessage message = (MiPushMessage) i.getSerializableExtra (PushMessageHelper.KEY_MESSAGE);
        // if (message != null) {
        // String customContent = message.getContent ();
        // if (customContent != null && customContent.length () != 0) {
        // try {
        // mPushInfo = JSON.parseObject (customContent, PushInfo.class);
        // } catch (Exception e) {
        // e.printStackTrace ();
        // }
        //
        // }
        // }
        // }
    }

    /**
     * 获取图片链接
     */
    private void getImageURL(){
        // String fileName = SharedPreferencesUtils.getString (AppConfig.SP_SPLASH_IMG_URL);
        // Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator ();
        // if (!fileName.equals ("")) {
        // File f = new File (AppConfig.CACHE_PATH_PIC + md5FileNameGenerator.generate (fileName));
        // if (f.exists ()) {
        // String desc = SharedPreferencesUtils.getString (AppConfig.SP_SPLASH_IMG_DESC);
        // descTextView.setText (desc);
        // loadImage ("file://" + AppConfig.CACHE_PATH_PIC + md5FileNameGenerator.generate (fileName));
        // } else {
        // loadImage ("drawable://" + R.drawable.ic_screen_default);
        //
        // }
        // } else {
        // loadImage ("drawable://" + R.drawable.ic_screen_default);
        // }
    }

    /**
     * 获取图片
     *
     * @param url
     */
    private void loadImage(final String url){
        // imageLoader = ImageLoader.getInstance ();
        // imageLoader.displayImage (url, imageView, options, new ImageLoadingListener () {
        //
        // @Override
        // public void onLoadingStarted(String arg0,View arg1){}
        //
        // @Override
        // public void onLoadingFailed(String arg0,View arg1,FailReason arg2){
        // goToActivity ();
        // }
        //
        // @Override
        // public void onLoadingComplete(String arg0,View arg1,Bitmap arg2){
        // // imageView1.startAnimation (alphaAnimation);
        // imageView.startAnimation (AnimationUtils.loadAnimation (SplashActivity.this, R.anim.splash_anim));
        // goToActivity ();
        // }
        //
        // @Override
        // public void onLoadingCancelled(String arg0,View arg1){
        // goToActivity ();
        // }
        // });
    }

    /**
     * 是否是新版本
     *
     * @return
     */
    private boolean isNewVersion(){
        int old_version_code = SharedPreferencesUtils.getInt (AppConfig.SP_VERSION_CODE);
        int new_version_code = AndroidUtils.getVersionCode (this);
        if (old_version_code < new_version_code) {
            // 新版本
            SharedPreferencesUtils.putInt (AppConfig.SP_VERSION_CODE, new_version_code);
            SharedPreferencesUtils.putBoolean (AppConfig.SP_NEW_VERSION, true);
            return true;
        } else {
            // 老版本
            SharedPreferencesUtils.putBoolean (AppConfig.SP_NEW_VERSION, false);
            return false;
        }
    }

    /**
     * 跳转
     */
    private void goToActivity(){
        handler.postDelayed (new Runnable () {

            @Override
            public void run(){
                Intent i = new Intent ();
                if (isNewVersion ()) {
                    i.setClass (SplashActivity.this, GuideActivity.class);
                } else {
                    if(GlobalApp.isLogin()){
                        i.setClass (SplashActivity.this, UnlockGesturePasswordActivity.class);
                    }else{
                        i.setClass(SplashActivity.this, LoginActivity.class);
                    }
                }
                startActivity (i);
                overridePendingTransition (R.anim.fade_in, R.anim.hold);
                finish ();
            }
        }, 2500);
    }

    /**
     * 获取绑定账户
     */
    private void getAccountsDataFromServer(){
        RequestParams params = new RequestParams ();
        params.put ("uid", GlobalApp.getUserInfo ().getUid ());
        params.put ("token", GlobalApp.getUserInfo ().getToken ());
        HttpUtil.get (HttpAPI.CIWEI_GET_ACCOUNTS, params, new TextHttpResponseHandler () {

            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}

            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ResultDataBean resultDataBean = JSON.parseObject (responseString, ResultDataBean.class);
                if (resultDataBean.isSucess ()) {
                    List<AppAccount> appAccounts = JSONUtil.getAppAccount (resultDataBean.getRetData ());
                    appAccountManager.deleteALL();
                    appAccountManager.insertList (appAccounts);
                    //
                    List<RegFieldBean> regFields = JSONUtil.getRegFields (resultDataBean.getRetData ());
                    // 保存regFields
                    SharedPreferencesUtils.putString (HttpAPI.CIWEI_GET_ACCOUNTS, JSON.toJSONString (regFields));
                }
            }
        });
    }

    /**
     * 获取app列表
     */
    private void getAppsDataFromServer(){
        RequestParams params = new RequestParams ();
        HttpUtil.get (HttpAPI.CIWEI_APPS, params, new TextHttpResponseHandler () {

            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){

            }

            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ResultDataBean resultDataBean = JSON.parseObject (responseString, ResultDataBean.class);
                if (resultDataBean.isSucess ()) {
                    List<App> apps = JSONUtil.getApps (resultDataBean.getRetData ());
                    appManager.insertList (apps);// 存储到数据库
                }
            }
        });
    }

    /***
     * 验证登录状态
     */
    private void getAccessDataFromServer(){
        RequestParams params = new RequestParams ();
        params.put ("uid", GlobalApp.getUserInfo ().getUid ());
        params.put ("token", GlobalApp.getUserInfo ().getToken ());
        HttpUtil.get (HttpAPI.CIWEI_ACCESS, params, new TextHttpResponseHandler () {
            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}
            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ResultDataBean resultDataBean = JSON.parseObject (responseString, ResultDataBean.class);
                if (resultDataBean.isSucess ()) {
                    UserFieldBean userFieldBean = JSON.parseObject (resultDataBean.getRetData (), UserFieldBean.class);
                    // GlobalApp.saveUserInfo (userFieldBean.getUserBean ());// 保存token到本地
                    List<Account> accounts = userFieldBean.getAccounts ();
                    SharedPreferencesUtils.putString (HttpAPI.CIWEI_LOGIN, JSON.toJSONString (accounts));
                    getAccountsDataFromServer ();
                    getAppsDataFromServer ();
                }
                if (resultDataBean.getRetCode () == 503) {
                    GlobalApp.clearUserInfo ();
                    ToastUtil.showToast (SplashActivity.this, R.string.login_error);
                }
            }
        });
    }
    /**
     * 基础数据网络请求
     */
    private void getBaseDataFromServer(){
        RequestParams params = new RequestParams ();
        HttpUtil.get (HttpAPI.CIWEI_BASEDATA, params, new TextHttpResponseHandler () {
            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}
            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ResultDataBean resultDataBean = JSON.parseObject (responseString, ResultDataBean.class);
                if (resultDataBean.isSucess ()) {
                    CommonBean commonBean = JSON.parseObject (resultDataBean.getRetData (), CommonBean.class);
                    FieldBean bean = commonBean.getFields ();
                    baseDataManager.insert (bean.getUsername ());
                    baseDataManager.insert (bean.getTel ());
                    baseDataManager.insert (bean.getEmail ());
                    baseDataManager.insert (bean.getIdCard ());
                    baseDataManager.insert (bean.getNickname ());
                    baseDataManager.insert (bean.getGender ());
                    baseDataManager.insert (bean.getBirthday ());
                    baseDataManager.insert (bean.getPassword ());
                    baseDataManager.insert (bean.getRealname ());
                }
            }
        });
    }
}
