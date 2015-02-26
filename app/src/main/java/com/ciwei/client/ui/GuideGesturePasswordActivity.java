package com.ciwei.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;

/**
 * Created by Vernon on 15/2/26.
 */
public class GuideGesturePasswordActivity extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesturepassword_guide);
        findViewById(R.id.gesturepwd_guide_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApp.getInstance().getLockPatternUtils().clearLock();
                Intent intent = new Intent(GuideGesturePasswordActivity.this,
                        CreateGesturePasswordActivity.class);
                // 打开新的Activity
                startActivity(intent);
                finish();
            }
        });
    }
}
