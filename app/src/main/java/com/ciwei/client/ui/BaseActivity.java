package com.ciwei.client.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Vernon on 15/2/11.
 */
public abstract class BaseActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (getLayoutResource ());
        initLayoutAndView();
    }

    /**
     * 初始化
     */
    private void initLayoutAndView() {
        initView();
        setListener();
        progressLogic();
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

    @Override
    public void onClick(View v){
        onClickEvent (v);
    }
}
