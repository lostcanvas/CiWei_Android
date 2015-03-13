package com.ciwei.client.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ciwei.client.R;
import com.ciwei.client.db.AppAccountManager;
import com.ciwei.client.model.AppAccount;
import com.ciwei.client.ui.activity.BindedAccountActivity;
import com.ciwei.client.utils.AndroidUtils;

import java.util.List;

/**
 * @首页界面 Created by Vernon on 15/3/3.
 */
public class HomeFragment extends BaseFragment {

    private View              rootView;
    private boolean           isPrepared;
    private AppAccountManager appAccountManager;
    private TextView          bind_account_tx;
    private List<AppAccount>  appAccounts;
    private RelativeLayout    rl_bind_account;

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        if (rootView == null) {
            rootView = inflater.inflate (R.layout.fragment_home, container, false);
            initView (rootView);
            setListener ();
            appAccountManager = AppAccountManager.getInstance (getActivity ());
            setHasOptionsMenu (true);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent ();
        if (parent != null) {
            parent.removeView (rootView);
        }
        isPrepared = true;
        return rootView;
    }

    @Override
    protected void setListener(){
        rl_bind_account.setOnClickListener (this);
    }

    @Override
    protected void progressLogic(){
        setData ();
    }

    @Override
    protected void onClickEvent(View view){
        switch (view.getId ()) {
            case R.id.rl_bind_account:
                Intent intent=new Intent();
                intent.setClass(getActivity(), BindedAccountActivity.class);
                AndroidUtils.startActivity(getActivity(),intent);
                break;

        }
    }

    @Override
    protected void initView(View rootView){
        bind_account_tx = (TextView) rootView.findViewById (R.id.home_bind_count_tx);
        rl_bind_account = (RelativeLayout) rootView.findViewById (R.id.rl_bind_account);
    }

    private void setData(){
        appAccounts = appAccountManager.getALL ();
        bind_account_tx.setText (appAccounts.size () + "");
    }
}
