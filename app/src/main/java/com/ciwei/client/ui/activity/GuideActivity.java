package com.ciwei.client.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ciwei.client.R;
import com.ciwei.client.adapter.GuidePagerAdapter;
import com.ciwei.client.view.JazzyViewPager.JazzyViewPager;

/**
 * 引导页
 * @author zhangqiang
 * Created by Vernon on 15/3/7.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private JazzyViewPager mPager;
    private GuidePagerAdapter mAdapter;
    private static final int[] layouts = { R.layout.adapter_guide_first,R.layout.adaprer_guide_two, R.layout.adapter_guide_three, R.layout.adapter_guide_four};
    // 定义一个ArrayList来存放View
    // private List<View> views = new ArrayList<View> ();
    // 底部小店图片
    private ImageView[]           points;
    // 记录当前选中位置
    private int                   currentIndex;
    @Override
    protected int getLayoutResource(){
        return R.layout.activity_guide_layout;
    }

    @Override
    protected void initView(){
        mPager = (JazzyViewPager) findViewById (R.id.guide_pager);
        initPoint();
        mPager.setTransitionEffect (JazzyViewPager.TransitionEffect.CubeOut);
        // mPager.setPageMargin (30);
        mAdapter = new GuidePagerAdapter (this,layouts);
        mPager.setOnPageChangeListener (this);
        mPager.setAdapter (mAdapter);
    }
    private void initPoint(){
        LinearLayout linearLayout = (LinearLayout) findViewById (R.id.ll_dots);
        points = new ImageView[layouts.length];
        // 循环取得小点图片
        for ( int i = 0 ; i <layouts.length; i++ ) {
            // 得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt (i);
            // 默认都设为灰色
            points[i].setEnabled (false);
            // 给每个小点设置监听
            // 设置位置tag，方便取出与当前位置对应
            points[i].setTag (i);
        }

        // 设置当面默认的位置
        currentIndex = 0;
        // 设置为白色，即选中状态
        points[currentIndex].setEnabled (true);
    }
    @Override
    protected void setListener(){

    }

    @Override
    protected void progressLogic(){

    }

    @Override
    protected void onClickEvent(View v){

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置底部小点选中状态
        setCurDot (position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon){
        if (positon < 0 || positon > layouts.length - 1 || currentIndex == positon) { return; }
        points[positon].setEnabled (true);
        points[currentIndex].setEnabled (false);
        currentIndex = positon;
    }
}
