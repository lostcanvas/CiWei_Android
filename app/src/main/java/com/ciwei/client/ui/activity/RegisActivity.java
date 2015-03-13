package com.ciwei.client.ui.activity;

import android.content.Intent;
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
import com.ciwei.client.model.UserBean;
import com.ciwei.client.model.UserFieldBean;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.JSONUtil;
import com.ciwei.client.utils.JsonParseUtil;
import com.ciwei.client.utils.ProgressDialogUtil;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.umeng.analytics.MobclickAgent;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * 注册界面
 * 
 * @author zhangqiang Created by Vernon on 15/2/26.
 */
public class RegisActivity extends BaseActivity {

    private static final String TAG = RegisActivity.class.getSimpleName ();
    private ImageButton         btn_back;
    private TextView            tv_title;
    private Button              btn_regis;
    private Button              btn_verify_code;
    private EditText            et_phone;                                  // 手机号
    private EditText            et_verify_code;                            // 验证码
    private EditText            et_email;                                  // 邮箱
    private EditText            et_password;                               // 密码

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_regis;
    }

    @Override
    protected void initView(){
        btn_back = (ImageButton) this.findViewById (R.id.include_left_btn);
        tv_title = (TextView) this.findViewById (R.id.include_title_tx);
        btn_regis = (Button) this.findViewById (R.id.btn_regis);
        btn_verify_code = (Button) this.findViewById (R.id.btn_get_code);
        et_email = (EditText) this.findViewById (R.id.regis_et_email);
        et_password = (EditText) this.findViewById (R.id.login_et_pwd);
        et_verify_code = (EditText) this.findViewById (R.id.regis_et_verify_code);
        et_phone = (EditText) this.findViewById (R.id.login_et_num);
        tv_title.setText ("注册");
    }

    @Override
    protected void onResume(){
        super.onResume ();
        MobclickAgent.onResume (this);
    }

    @Override
    protected void onPause(){
        super.onPause ();
        MobclickAgent.onPause (this);
    }

    @Override
    protected void setListener(){
        btn_back.setOnClickListener (this);
        btn_regis.setOnClickListener (this);
        btn_verify_code.setOnClickListener (this);
    }

    @Override
    protected void progressLogic(){

    }

    /**
     * 网络获取数据
     */
    private void getDataFromServer(String num,String pwd,String email,String code){
        RequestParams params = new RequestParams ();
        params.put ("tel", num);
        params.put ("password", pwd);
        params.put ("code", code);
        params.put ("email", email);
        HttpUtil.post (HttpAPI.CIWEI_REGISTER, params, new TextHttpResponseHandler () {
            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}
            @Override
            public void onStart(){
                super.onStart ();
                ProgressDialogUtil.showCustomProgressDialog (RegisActivity.this,getResources().getString(R.string.loading));
            }
            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ProgressDialogUtil.dismiss ();
                ResultDataBean resultDataBean = JSON.parseObject(responseString, ResultDataBean.class);
                if (resultDataBean.isSucess ()) {
                    UserFieldBean userFieldBean = JSON.parseObject(resultDataBean.getRetData(), UserFieldBean.class);
                    GlobalApp.saveUserInfo (userFieldBean.getUserBean ());// 保存token到本地
                    Intent intent = new Intent (RegisActivity.this,CreateGesturePasswordActivity.class);
                    RegisActivity.this.startActivity (intent);
                }
                switch (resultDataBean.getRetCode()){
                    case 508:
                        ToastUtil.showToast(RegisActivity.this,"该用户已注册");
                        break;
                }
            }
            @Override
            public void onFinish(){}
        });
    }
    @Override
    protected void onClickEvent(View v){
        switch (v.getId ()) {
            case R.id.include_left_btn://
                AndroidUtils.activityFinish (this);
                break;
            case R.id.btn_regis:// 注册
                String num = et_phone.getText ().toString ();
                String email = et_email.getText ().toString ();
                String password = et_password.getText ().toString ();
                String code = et_verify_code.getText ().toString ();
                if (num.equals ("") && num.length () < 11) {
                    ToastUtil.showToast (this, "请输入正确的手机号");
                } else if (!AndroidUtils.isEmail (email)) {
                    ToastUtil.showToast (this, "请输入正确的邮箱");
                } else if (password.equals ("") || password.length () < 6) {
                    ToastUtil.showToast (this, "请输6-12位密码");
                } else if (code.equals ("")) {
                    ToastUtil.showToast (this, "请输入验证码");
                } else {
                    getDataFromServer (num, password, email, code);
                }
                break;
            case R.id.btn_get_code:// 获取验证码
                String phone = et_phone.getText ().toString ();
                if (phone.equals ("") && phone.length () < 11) {
                    ToastUtil.showToast (this, "请输入正确的手机号");
                } else {
                    getCodeFromServer (phone);
                }
                break;
        }
    }

    /**
     * 网络获取数据
     */
    private void getCodeFromServer(String num){
        RequestParams params = new RequestParams ();
        params.put ("tel", num);
        HttpUtil.post (HttpAPI.CIWEI_GET_VERIF_CODE, params, new TextHttpResponseHandler () {
            @Override
            public void onStart(){
                super.onStart ();
                ProgressDialogUtil.showCustomProgressDialog (RegisActivity.this,getResources().getString(R.string.loading));
            }
            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}
            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ProgressDialogUtil.dismiss ();
                statusCode = JSONUtil.getStatusCode (responseString);
                if (statusCode == 200) {
                    btn_verify_code.setText ("重新获取");
                }
            }
            @Override
            public void onFinish(){}
        });
    }

}
