package com.ciwei.client.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ciwei.client.R;
import com.ciwei.client.http.HttpAPI;
import com.ciwei.client.http.HttpUtil;
import com.ciwei.client.utils.JSONUtil;
import com.ciwei.client.utils.ProgressDialogUtil;
import com.ciwei.client.utils.ToastUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import org.apache.http.Header;

/**
 * 找回密码
 * 
 * @author zhangqiang Created by Vernon on 15/2/28.
 */
public class ForgetPwdActivity extends BaseActivity {

    private ImageButton btn_back;

    private TextView    tv_title;

    private EditText    et_phone_num;

    private EditText    et_password;

    private EditText    et_verify_code;

    private Button      btn_next;
    private Button      btn_get_code;

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_find_pwd;
    }

    @Override
    protected void initView(){
        btn_back = (ImageButton) this.findViewById (R.id.include_left_btn);
        tv_title = (TextView) this.findViewById (R.id.include_title_tx);
        et_password = (EditText) this.findViewById (R.id.login_et_pwd);
        et_phone_num = (EditText) this.findViewById (R.id.login_et_num);
        et_verify_code = (EditText) this.findViewById (R.id.login_verify_code);
        btn_next = (Button) this.findViewById (R.id.btn_next);
        btn_get_code = (Button) this.findViewById (R.id.btn_get_code);
        tv_title.setText ("找回密码");
    }

    @Override
    protected void setListener(){
        btn_back.setOnClickListener (this);
        btn_next.setOnClickListener (this);
        btn_get_code.setOnClickListener (this);
    }

    @Override
    protected void progressLogic(){

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
                ProgressDialogUtil.showCustomProgressDialog (ForgetPwdActivity.this,getResources().getString(R.string.loading));
            }
            @Override
            public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){}

            @Override
            public void onSuccess(int statusCode,Header[] headers,String responseString){
                ProgressDialogUtil.dismiss ();
                statusCode = JSONUtil.getStatusCode (responseString);
                if (statusCode == 200) {
                    btn_get_code.setEnabled (true);
                    btn_get_code.setText ("有效时间5分钟");
                }
            }
            @Override
            public void onFinish(){}
        });

    }

    @Override
    protected void onClickEvent(View v){
        switch (v.getId ()) {
            case R.id.btn_next://下一步

                break;
            case R.id.btn_get_code:
                String num = et_phone_num.getText ().toString ();
                if (!num.equals ("")) {
                    getCodeFromServer (num);
                } else {
                    ToastUtil.showToast (this, "手机号不能为空");
                }
                break;
        }
    }
}
