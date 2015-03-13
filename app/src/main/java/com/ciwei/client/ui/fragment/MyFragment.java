package com.ciwei.client.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciwei.client.R;

/**
 * @author zhangqiang
 * Created by Vernon on 15/3/3.
 */
public class MyFragment extends BaseFragment{
    private View rootView;
    private boolean isPrepared;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_my, container, false);
            initView(rootView);
            setListener();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        isPrepared = true;
        return rootView;
    }
    @Override
    protected void setListener() {


    }

    @Override
    protected void progressLogic() {

    }

    @Override
    protected void onClickEvent(View view) {

    }
    @Override
    protected void initView(View rootView) {
        
    }
}
