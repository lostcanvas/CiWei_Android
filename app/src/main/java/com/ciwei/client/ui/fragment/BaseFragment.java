package com.ciwei.client.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Vernon on 15/3/8.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint (isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible ();
        } else {
            isVisible = false;
            onInvisible ();
        }
    }

    @Override
    public void onResume(){
        super.onResume ();
        if (getUserVisibleHint ()) {
            onVisible ();
        }
    }

    /**
     * 初始化组件
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 设置监听器
     */
    protected abstract void setListener();

    /**
     * 处理逻辑
     */
    protected abstract void progressLogic();

    /**
     * 用户可见
     */
    protected void onVisible(){
        progressLogic ();
    }

    /**
     * 用户不可见
     */
    protected void onInvisible(){}

    /**
     * 处理点击事件
     *
     * @param view
     */
    protected abstract void onClickEvent(View view);

    @Override
    public void onClick(View v){
        onClickEvent (v);
    }
}
