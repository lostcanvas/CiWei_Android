package com.ciwei.client.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSON;
import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.db.AppAccountManager;
import com.ciwei.client.db.AppManager;
import com.ciwei.client.http.HttpAPI;
import com.ciwei.client.http.HttpUtil;
import com.ciwei.client.model.Account;
import com.ciwei.client.model.App;
import com.ciwei.client.model.AppAccount;
import com.ciwei.client.model.RegFieldBean;
import com.ciwei.client.model.ResultDataBean;
import com.ciwei.client.model.UserFieldBean;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.CountUtils;
import com.ciwei.client.utils.JSONUtil;
import com.ciwei.client.utils.ProgressDialogUtil;
import com.ciwei.client.utils.SharedPreferencesUtils;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.umeng.analytics.MobclickAgent;

import org.apache.http.Header;

import java.util.List;

/**
 * @author zhangqiang
 *         Created by Vernon on 15/2/26.
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText et_phone_num;

    private EditText et_password;
    private EditText et_email;
    private EditText et_code;
    private Button btn_login;
    private Button btn_forget_pwd;
    private Button btn_login_temp;
    private Button btn_get_code;
    private boolean isLogin = true;
    private LinearLayout ll_email;
    private LinearLayout ll_verify;
    private View email_line, line_code;
    private AppAccountManager appAccountManager;
    private AppManager appManager;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        appAccountManager=AppAccountManager.getInstance(this);
        appManager=AppManager.getInstance(this);
        et_password = (EditText) this.findViewById(R.id.login_password_et);
        et_phone_num = (EditText) this.findViewById(R.id.login_phone_et);
        et_email = (EditText) this.findViewById(R.id.login_email_et);
        et_code = (EditText) this.findViewById(R.id.regis_et_verify_code);
        btn_login = (Button) this.findViewById(R.id.btn_login);
        btn_forget_pwd = (Button) this.findViewById(R.id.btn_forget_pwd);
        btn_login_temp = (Button) this.findViewById(R.id.btn_temp_login);
        ll_email = (LinearLayout) this.findViewById(R.id.ll_email);
        ll_verify = (LinearLayout) this.findViewById(R.id.ll_verify);
        btn_get_code = (Button) this.findViewById(R.id.btn_get_code);
        email_line = this.findViewById(R.id.line_email_line);
        line_code = this.findViewById(R.id.line_code);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        GlobalApp.notShowLock = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void setListener() {
        // btn_back.setOnClickListener (this);
        btn_login.setOnClickListener(this);
        btn_forget_pwd.setOnClickListener(this);
        btn_login_temp.setOnClickListener(this);
        btn_get_code.setOnClickListener(this);
    }

    @Override
    protected void progressLogic() {

    }

    /**
     * 网络获取数据
     */
    private void getDataFromServer(String num, String pwd) {
        RequestParams params = new RequestParams();
        params.put("tel", num);
        params.put("password", pwd);
        HttpUtil.post(HttpAPI.CIWEI_LOGIN, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                ProgressDialogUtil.dismiss();
                ToastUtil.showToast(LoginActivity.this, "未连接到服务器");
            }

            @Override
            public void onStart() {
                super.onStart();
                ProgressDialogUtil.showCustomProgressDialog(LoginActivity.this,getResources().getString(R.string.loading));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ProgressDialogUtil.dismiss();
                ResultDataBean resultDataBean = JSON.parseObject(responseString, ResultDataBean.class);
                if (resultDataBean.isSucess()) {
                    UserFieldBean userFieldBean = JSON.parseObject(resultDataBean.getRetData(), UserFieldBean.class);
                    GlobalApp.saveUserInfo(userFieldBean.getUserBean());// 保存token到本地

                    List<Account> accounts = userFieldBean.getAccounts();
                    SharedPreferencesUtils.putString(HttpAPI.CIWEI_LOGIN, JSON.toJSONString(accounts));
                    if (GlobalApp.getInstance().getLockPatternUtils().savedPatternExists()) {
                        Intent intent = new Intent(LoginActivity.this, UnlockGesturePasswordActivity.class);
                        LoginActivity.this.startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginActivity.this, GuideGesturePasswordActivity.class);
                        LoginActivity.this.startActivity(intent);
                    }
                    getAccountsDataFromServer();
                    getAppsDataFromServer();
                    GlobalApp.getInstance().addActivity(LoginActivity.this);
                    GlobalApp.getInstance().exit();
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @Override
    protected void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.include_left_btn:
                AndroidUtils.activityFinish(this);
                break;
            case R.id.btn_login:
                if (isLogin) {
                    setLogin();
                } else {
                    setRegister();
                }
                break;
            case R.id.btn_forget_pwd:// 忘记密码页面
                Intent intent = new Intent(this, ForgetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_temp_login:// 注册或登录
                if (isLogin) {
                    btn_login_temp.setText("登录");
                    btn_login.setText("注册");
                    ll_email.setVisibility(View.VISIBLE);
                    email_line.setVisibility(View.VISIBLE);
                    ll_verify.setVisibility(View.VISIBLE);
                    line_code.setVisibility(View.VISIBLE);
                    isLogin = false;
                } else {
                    btn_login_temp.setText("注册");
                    btn_login.setText("登录");
                    ll_verify.setVisibility(View.GONE);
                    ll_email.setVisibility(View.GONE);
                    email_line.setVisibility(View.GONE);
                    line_code.setVisibility(View.GONE);
                    isLogin = true;
                }
                break;
            case R.id.btn_get_code://获取验证码
                String phone = et_phone_num.getText().toString();
                if (phone.equals("") && phone.length() < 11) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                } else {
                    getCodeFromServer(phone);
                    new CountUtils(60000,1000,btn_get_code,LoginActivity.this).start();
                }
                break;
        }
    }

    /**
     * 网络获取数据
     */
    private void getCodeFromServer(String num) {
        RequestParams params = new RequestParams();
        params.put("tel", num);
        HttpUtil.post(HttpAPI.CIWEI_GET_VERIF_CODE, params, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                ProgressDialogUtil.showCustomProgressDialog(LoginActivity.this,getResources().getString(R.string.loading));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ProgressDialogUtil.dismiss();
                statusCode = JSONUtil.getStatusCode(responseString);
                if (statusCode == 200) {
                    btn_get_code.setTextColor(Color.RED);
                    btn_get_code.setText("重新获取");
                }
            }
            @Override
            public void onFinish() {
            }
        });
    }

    /***
     *
     */
    private void setRegister() {
        String num = et_phone_num.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String code = et_code.getText().toString();
        if (num.equals("") && num.length() < 11) {
            ToastUtil.showToast(this, "请输入正确的手机号");
        } else if (!AndroidUtils.isEmail(email)) {
            ToastUtil.showToast(this, "请输入正确的邮箱");
        } else if (password.equals("") || password.length() < 6) {
            ToastUtil.showToast(this, "请输6-12位密码");
        } else if (code.equals("")) {
            ToastUtil.showToast(this, "请输入验证码");
        } else {
            getRegisterDataFromServer(num, password, email, code);
        }
    }

    /**
     * 网络获取数据
     */
    private void getRegisterDataFromServer(String num, String pwd, String email, String code) {
        RequestParams params = new RequestParams();
        params.put("tel", num);
        params.put("password", pwd);
        params.put("code", code);
        params.put("email", email);
        HttpUtil.post(HttpAPI.CIWEI_REGISTER, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }
            @Override
            public void onStart() {
                super.onStart();
                ProgressDialogUtil.showCustomProgressDialog(LoginActivity.this,getResources().getString(R.string.loading));
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ProgressDialogUtil.dismiss();
                ResultDataBean resultDataBean = JSON.parseObject(responseString, ResultDataBean.class);
                if (resultDataBean.isSucess()) {
                    UserFieldBean userFieldBean = JSON.parseObject(resultDataBean.getRetData(), UserFieldBean.class);
                    GlobalApp.saveUserInfo(userFieldBean.getUserBean());// 保存token到本地
                    Intent intent = new Intent(LoginActivity.this, CreateGesturePasswordActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
                switch (resultDataBean.getRetCode()) {
                    case 508:
                        ToastUtil.showToast(LoginActivity.this, "该用户已注册");
                        break;
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }
    /**
     * 获取绑定账户
     */
    private void getAccountsDataFromServer(){
        RequestParams params = new RequestParams ();
        params.put ("uid", GlobalApp.getUserInfo ().getUid ());
        params.put ("token", GlobalApp.getUserInfo ().getToken ());
        HttpUtil.get(HttpAPI.CIWEI_GET_ACCOUNTS, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ResultDataBean resultDataBean = JSON.parseObject(responseString, ResultDataBean.class);
                if (resultDataBean.isSucess()) {
                    List<AppAccount> appAccounts = JSONUtil.getAppAccount(resultDataBean.getRetData());
                    appAccountManager.deleteALL();
                    appAccountManager.insertList(appAccounts);
                    //
                    List<RegFieldBean> regFields = JSONUtil.getRegFields(resultDataBean.getRetData());
                    // 保存regFields
                    SharedPreferencesUtils.putString(HttpAPI.CIWEI_GET_ACCOUNTS, JSON.toJSONString(regFields));
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

    /**
     * 设置登录，请求
     */
    private void setLogin() {
        String num = et_phone_num.getText().toString();
        String password = et_password.getText().toString();
        if (num.equals("") || password.equals("")) {
            ToastUtil.showToast(this, "请输入用户名或密码");
        } else {
            getDataFromServer(num, password);
        }
    }
}
