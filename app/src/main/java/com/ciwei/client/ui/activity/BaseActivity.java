package com.ciwei.client.ui.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.utils.ScreenListener;
import com.umeng.socialize.controller.UMServiceFactory;

import java.util.List;

/**
 * @author zhangqiang
 *         Created by Vernon on 15/2/11.
 */
public abstract class BaseActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = BaseActivity.class.getSimpleName();
    public com.umeng.socialize.controller.UMSocialService mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(getLayoutResource());
        initLayoutAndView();
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
    }

    /**
     * 初始化
     */
    private void initLayoutAndView() {
        initView();
        setListener();
        progressLogic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLockParams();
    }

    private void setLockParams() {
        if (!GlobalApp.isActive) {
            // app 从后台唤醒，进入前台
            GlobalApp.isActive = true;
            if (GlobalApp.isBtoF) {
                GlobalApp.isBtoF = false;
                if (!GlobalApp.isShowLock && !GlobalApp.notShowLock) {
                    gotoUnLock(this);
                }
            }
        }
        if (GlobalApp.sl == null) {
            GlobalApp.sl = new ScreenListener(this);
            GlobalApp.sl.begin(new ScreenListener.ScreenStateListener() {
                @Override
                public void onScreenOn() {
                    /** 排除第一次监听 显示亮屏 */
                    GlobalApp.ligthCount++;
                    if (GlobalApp.isActive && !GlobalApp.isShowLock && (GlobalApp.ligthCount > 1) && !GlobalApp.notShowLock) {
                        gotoUnLock(getApplicationContext());
                    }
                }
                @Override
                public void onScreenOff() {
                }

                @Override
                public void onUserPresent() {
                }
            });
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (!isAppOnForeground()) {
//            // app 进入后台
//            GlobalApp.isActive = false;
//            GlobalApp.isBtoF = true;
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(GlobalApp.sl!=null){
            GlobalApp.sl.unregisterListener();
        }
    }

    /**
     * 进入解锁界面
     *
     * @param context
     */
    private void gotoUnLock(Context context) {
        Intent i = new Intent(context, UnlockGesturePasswordActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int getLayoutResource();

    /**
     * findViewById
     */
    protected abstract void initView();

    /**
     * 设置监听器
     */
    protected abstract void setListener();

    /**
     * 逻辑处理
     */
    protected abstract void progressLogic();

    /**
     * 点击事件
     *
     * @param v
     */
    protected abstract void onClickEvent(View v);

    /**
     * 判断当前应用程序处于前台还是后台
     */
    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        onClickEvent(v);
    }
}
