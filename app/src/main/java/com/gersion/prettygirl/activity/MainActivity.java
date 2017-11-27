package com.gersion.prettygirl.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.view.TitleView;

public class MainActivity extends BaseActivity {

    private TitleView mTitleView;
    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitleView = findView(R.id.titleView);
        mTablayout = findView(R.id.tablayout);
        mViewPager = findView(R.id.viewPager);

        mTitleView.setTitleText("美图show");
        mTitleView.setBackVisiable(false);
        mTablayout.setupWithViewPager(mViewPager);
//        mTablayout.setTabsFromPagerAdapter(mHomePagerAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
