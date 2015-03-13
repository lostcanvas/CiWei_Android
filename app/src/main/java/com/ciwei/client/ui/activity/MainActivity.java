package com.ciwei.client.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.ciwei.client.R;
import com.ciwei.client.app.GlobalApp;
import com.ciwei.client.ui.fragment.HomeFragment;
import com.ciwei.client.ui.fragment.MyFragment;
import com.ciwei.client.utils.LogUtil;
import com.ciwei.client.view.MyFragmentTabHost;

/**
 * @author
 * @author zhangqiang 主页面
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MyFragmentTabHost mTabHost;
    private ImageButton btn_scan_qr; // 扫描二维码
    private RadioButton radioButtonHome, radioButtonMy;
    private ImageButton btnRightAdd;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_scan_qr = (ImageButton) this.findViewById(R.id.btn_scan_qr);
        radioButtonHome = (RadioButton) this.findViewById(R.id.radio_btn_home);
        btnRightAdd= (ImageButton) this.findViewById(R.id.include_right_btn);
        btnRightAdd.setVisibility(View.GONE);
        radioButtonMy = (RadioButton) this.findViewById(R.id.radio_btn_my);
        mTabHost = (MyFragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 初始化Tab
        TabHost.TabSpec homeTabSpec = mTabHost.newTabSpec("home").setIndicator("home");
        TabHost.TabSpec personTabSpec = mTabHost.newTabSpec("personal").setIndicator("personal");
        mTabHost.addTab(homeTabSpec, HomeFragment.class, null);
        mTabHost.addTab(personTabSpec, MyFragment.class, null);
        mTabHost.setCurrentTab(0);
    }
    @Override
    protected void setListener() {
        btn_scan_qr.setOnClickListener(this);
        radioButtonMy.setOnClickListener(this);
        radioButtonHome.setOnClickListener(this);
    }
    @Override
    protected void progressLogic() {

    }

    @Override
    protected void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_scan_qr:
                gotoScanQrCode();
                break;
            case R.id.radio_btn_home://首页
                mTabHost.setCurrentTab(0);
                break;
            case R.id.radio_btn_my://我的
                mTabHost.setCurrentTab(1);
                break;
        }
    }

    /**
     * 扫描二维码跳转
     */
    private void gotoScanQrCode() {
        Intent intent = new Intent(this, ScanQRCodeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
