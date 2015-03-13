package com.ciwei.client.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.db.AppAccountManager;
import com.ciwei.client.http.HttpAPI;
import com.ciwei.client.http.HttpUtil;
import com.ciwei.client.model.ResultDataBean;
import com.ciwei.client.model.SortModel;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.DialogUtil;
import com.ciwei.client.utils.MD5Util;
import com.ciwei.client.utils.ProgressDialogUtil;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.apache.http.Header;

import java.io.Serializable;

/**
 * @author
 * zhangqiang
 * Created by Vernon on 15/3/10.
 */
public class AccountDetailActivity extends BaseActivity {

    private static final String TAG =AccountDetailActivity.class.getSimpleName();
    public static final String  EXTRA_KEY      = "app";

    private SortModel bean;

    private TextView textTitle;

    private ImageButton rightBtn;
    // 应用图标
    private ImageView logoApp;

    private Button unlockBindBtn;
    //授权次数
    private TextView textViewAccountAuthorize;
    //
    private TextView textViewAccountName;
    // 标题
    private TextView textViewAccountTitle;

    private AppAccountManager appAccountManager;

    private TextView textTopTitle;

    private ImageLoader imageLoader;

    private DisplayImageOptions options;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_account_detail;
    }

    @Override
    protected void initView() {
        bean = (SortModel) getIntent().getSerializableExtra(BindedAccountActivity.EXTRA_KEY);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .build();
        appAccountManager=AppAccountManager.getInstance(this);
        textTitle = (TextView) this.findViewById(R.id.include_title_tx);
        textTitle.setText(bean==null?"":bean.getAppAccount().getU());
        rightBtn = (ImageButton) this.findViewById(R.id.include_right_btn);
        unlockBindBtn = (Button) this.findViewById(R.id.unlock_bind_btn);
        textViewAccountAuthorize = (TextView) this.findViewById(R.id.account_authorize_tx);
        textViewAccountName = (TextView) this.findViewById(R.id.account_email_tx);
        textViewAccountTitle = (TextView) this.findViewById(R.id.account_title_tx);
        logoApp = (ImageView) this.findViewById(R.id.account_app_logo);
    }

    @Override
    protected void setListener() {
        rightBtn.setOnClickListener(this);
        unlockBindBtn.setOnClickListener(this);
        findViewById(R.id.account_update_pwd).setOnClickListener(this);
    }

    @Override
    protected void progressLogic() {
        setData();
    }

    private void setData() {
        if (bean == null) {
            return;
        }
        imageLoader.displayImage(bean.getApp().getLogo(), logoApp, options);
        textViewAccountTitle.setText(bean.getApp().getAppName());
        textViewAccountName.setText(bean.getAppAccount().getU());
        textViewAccountAuthorize.setText("1");
    }

    @Override
    protected void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.include_right_btn:// 添加按钮

                break;
            case R.id.unlock_bind_btn:// 解除绑定
                unlockBind();
                break;
            case R.id.account_update_pwd:// 修改密码
                Intent i=new Intent(this,UpdatePwdActivity.class);
                i.putExtra(EXTRA_KEY, (Serializable)bean);
                AndroidUtils.startActivity(this,i);
                break;
        }
    }
    private void unlockBind() {
        DialogUtil.showConfirmDialog(this, getResources().getString(R.string.unbind), new DialogUtil.ConfirmListener() {
            @Override
            public void sure() {
                getDataFromServer();
            }
            @Override
            public void cancle() {
            }

        });
    }

    /**
     * @zhangqiang 解除绑定账号
     */
    private void getDataFromServer() {
        RequestParams params = new RequestParams();
        String sign = MD5Util.MD5(bean.getApp().getAppId() + GlobalApp.getUserInfo().getToken()+ GlobalApp.getUserInfo().getUid() + bean.getAppAccount().getU() + GlobalApp.getUserInfo().getUid());
        params.put("uid", GlobalApp.getUserInfo().getUid());
        params.put("token", GlobalApp.getUserInfo().getToken());
        params.put("account", bean.getAppAccount().getU());
        params.put("appId",bean.getApp().getAppId());
        params.put("sign", sign.toLowerCase().substring(6, 30));
        HttpUtil.post(HttpAPI.CIWEI_DEL_ACCOUNT, params, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                ProgressDialogUtil.showCustomProgressDialog(AccountDetailActivity.this,getResources().getString(R.string.loading_msg));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                ProgressDialogUtil.dismiss();
                ToastUtil.showToast(AccountDetailActivity.this,R.string.fail);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ProgressDialogUtil.dismiss();
                ResultDataBean resultBean = JSON.parseObject(responseString, ResultDataBean.class);
                if (resultBean.isSucess()) {
                    appAccountManager.deleteById(Integer.parseInt(bean.getAppAccount().getApp_id()));
                    Intent i=new Intent(AccountDetailActivity.this,BindedAccountActivity.class);
                    setResult(RESULT_OK,i);
                    ToastUtil.showToast(AccountDetailActivity.this, R.string.success);
                    AndroidUtils.activityFinish(AccountDetailActivity.this);
                }
                if(resultBean.getMessage()!=null){
                    ToastUtil.showToast(AccountDetailActivity.this,resultBean.getMessage());
                }
            }
        });
    }

}
