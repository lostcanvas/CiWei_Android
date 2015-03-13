package com.ciwei.client.ui.activity;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.http.HttpAPI;
import com.ciwei.client.http.HttpUtil;
import com.ciwei.client.model.ResultDataBean;
import com.ciwei.client.model.SortModel;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.DialogUtil;
import com.ciwei.client.utils.LogUtil;
import com.ciwei.client.utils.MD5Util;
import com.ciwei.client.utils.RadomStirngUtil;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * 修改密码
 * 
 * @author zhangqiang Created by Vernon on 15/3/11.
 */
public class UpdatePwdActivity extends BaseActivity {

    private static final String TAG = UpdatePwdActivity.class.getSimpleName ();
    private Button              eyeBtnImg;

    private EditText            editTextPwd;

    private Button              bornPwdBtn;

    private TextView            textTopTitle;

    private ImageButton         btnRightAdd;

    private ImageButton         btnLeftBack;

    private SortModel           bean;

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void initView(){
        bean = (SortModel) getIntent ().getSerializableExtra (AccountDetailActivity.EXTRA_KEY);
        eyeBtnImg = (Button) this.findViewById (R.id.update_view_eye);
        editTextPwd = (EditText) this.findViewById (R.id.update_new_pwd);
        bornPwdBtn = (Button) this.findViewById (R.id.update_random_pwd);
        textTopTitle = (TextView) this.findViewById (R.id.include_title_tx);
        btnRightAdd = (ImageButton) this.findViewById (R.id.include_right_btn);
        btnLeftBack = (ImageButton) this.findViewById (R.id.include_left_btn);
        btnLeftBack.setVisibility (View.VISIBLE);
        btnRightAdd.setVisibility (View.GONE);
        textTopTitle.setText ("更改密码");
    }

    @Override
    protected void setListener(){
        bornPwdBtn.setOnClickListener (this);
        btnLeftBack.setOnClickListener (this);
        eyeBtnImg.setOnClickListener (this);
        findViewById (R.id.update_sure_btn).setOnClickListener (this);
    }

    private boolean ischecked = false;

    @Override
    protected void progressLogic(){}

    @Override
    protected void onClickEvent(View v){
        switch (v.getId ()) {
            case R.id.update_view_eye:// 实现隐藏密码
                if (!ischecked) {// 显示密码
                    // 显示密码
                    editTextPwd.setInputType (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ischecked = true;
                } else {
                    editTextPwd.setInputType (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ischecked = false;
                }
                break;
            case R.id.update_random_pwd:// 生成随机密码
                ischecked = true;
                editTextPwd.setInputType (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                editTextPwd.setText (RadomStirngUtil.getRandomString (12));
                break;
            case R.id.include_left_btn:// 返回键
                AndroidUtils.activityFinish (this);
                break;
            case R.id.update_sure_btn:// 确定
                if (bean == null) { return; }
                if (editTextPwd.getText ().toString ().equals ("")) {
                    ToastUtil.showToast (UpdatePwdActivity.this, getResources ().getString (R.string.update_pwd));
                } else {
                    showSureDialog ();
                }
                break;
        }
    }

    private void showSureDialog(){
        DialogUtil.showConfirmDialog (this, getResources ().getString (R.string.update_pwd_msg), new DialogUtil.ConfirmListener () {
            @Override
            public void sure(){
                getDataFromServer (editTextPwd.getText ().toString ());
            }
            @Override
            public void cancle(){
            }
        });
    }

    private void getDataFromServer(String newpwd){
        RequestParams params = new RequestParams ();
        String fields = "{username:" + bean.getAppAccount ().getU () + ",password:" + newpwd + "}";
        LogUtil.i (TAG, fields + "=======================");
        String sign = MD5Util.MD5 (bean.getApp ().getAppId () + GlobalApp.getUserInfo ().getToken () + GlobalApp.getUserInfo ().getUid () + fields + GlobalApp.getUserInfo ().getUid ());
        params.put ("uid", GlobalApp.getUserInfo ().getUid ());
        params.put ("appId", bean.getApp ().getAppId ());
        params.put ("fields", fields);
        params.put ("token", GlobalApp.getUserInfo ().getToken ());
        params.put ("sign", sign.toLowerCase ().substring (6, 30));
        HttpUtil.post (HttpAPI.CIWEI_REST_CHPWD, params, new TextHttpResponseHandler () {
            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}
            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ResultDataBean resultDataBean = JSON.parseObject (responseString, ResultDataBean.class);
                if (resultDataBean.isSucess ()) {
                    ToastUtil.showToast (UpdatePwdActivity.this, "密码修改成功");
                    AndroidUtils.activityFinish (UpdatePwdActivity.this);
                }
                if (resultDataBean.getMessage () != null) {
                    ToastUtil.showToast (UpdatePwdActivity.this, resultDataBean.getMessage ());
                }
            }
        });
    }
}
