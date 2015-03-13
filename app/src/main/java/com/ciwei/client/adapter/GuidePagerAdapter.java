package com.ciwei.client.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ciwei.client.R;
import com.ciwei.client.ui.activity.LoginActivity;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.view.JazzyViewPager.JazzyViewPager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Vernon on 15/3/7.
 */
public class GuidePagerAdapter extends PagerAdapter {

    private Context        mContext;
    private LayoutInflater mInflater;
    // 界面列表
    private int[]          layouts;

    @Override
    public int getCount(){
        if (layouts != null) { return layouts.length; }
        return layouts.length;
    }

    public GuidePagerAdapter(Context mContext, int[] layouts) {
        super ();
        this.mContext = mContext;
        this.layouts = layouts;
        mInflater = LayoutInflater.from (this.mContext);
    }

    @Override
    public boolean isViewFromObject(View arg0,Object arg1){
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        View v = mInflater.inflate (layouts[position], null);
        if (position == 3) {
            Button beginLoginButton = (Button) v.findViewById (R.id.btn_next);
            beginEvent (beginLoginButton);
        }
        ((JazzyViewPager) container).addView (v, 0);
        ((JazzyViewPager) container).setObjectForPosition (v, position);
        return v;

    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView ((View) object);
    }

    private void beginEvent(View view){
        view.setOnClickListener (new View.OnClickListener () {

            @Override
            public void onClick(View arg0){
                Intent intent = new Intent (mContext,LoginActivity.class);
                AndroidUtils.startActivity ((Activity) mContext, intent);
                ((Activity) mContext).finish ();
            }
        });
    }

}
