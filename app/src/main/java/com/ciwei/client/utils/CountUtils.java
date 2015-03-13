package com.ciwei.client.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

import com.ciwei.client.R;

/**
 * 倒计时
 * 
 * @author zhangqiang Created by Vernon on 15/3/8.
 */
public class CountUtils extends CountDownTimer {

    /**
     * @param millisInFuture
     *            The number of millis in the future from the call to {@link #start()} until the countdown is done and {@link #onFinish()} is called.
     * @param countDownInterval
     *            The interval along the way to receive {@link #onTick(long)} callbacks.
     */
    private Button  btn_time;
    private Context context;

    public CountUtils(long millisInFuture, long countDownInterval, Button btn_time, Context context) {
        super (millisInFuture, countDownInterval);
        this.btn_time = btn_time;
        this.context=context;
    }

    @Override
    public void onTick(long millisUntilFinished){
        btn_time.setTextColor(Color.RED);
        btn_time.setText ("重新获取" + "(" + millisUntilFinished / 1000 + ")");
        btn_time.setEnabled(false);
    }

    @Override
    public void onFinish(){
        btn_time.setText ("获取验证码");
        btn_time.setTextColor (context.getResources().getColor(R.color.color_333333));
        btn_time.setEnabled(true);
    }
}
