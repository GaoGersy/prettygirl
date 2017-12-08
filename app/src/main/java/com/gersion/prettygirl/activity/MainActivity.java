package com.gersion.prettygirl.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gersion.library.http.HttpHandler;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.HomePagerAdapter;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.bean.GirlType;
import com.gersion.prettygirl.bean.GirlTypeResultBean;
import com.gersion.prettygirl.constants.AppConstants;
import com.gersion.prettygirl.fragment.CommonFragment;
import com.gersion.prettygirl.utils.LoggerUtils;
import com.gersion.prettygirl.view.TitleView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements HttpHandler.ResultCallBack<GirlTypeResultBean> {

    private TitleView mTitleView;
//    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private HttpHandler mHttpHandler;
    private SmartTabLayout mViewPagerTab;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitleView = findView(R.id.titleView);
//        mTablayout = findView(R.id.tablayout);
        mViewPager = findView(R.id.viewPager);
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        mTitleView.setTitleText("美图show");
        mTitleView.setBackVisiable(false);
//        mTablayout.setupWithViewPager(mViewPager);
//        mTablayout.setTabsFromPagerAdapter(mHomePagerAdapter);

    }

    @Override
    protected void initData() {
        String url = AppConstants.BASE_URL+"image/getAllImageCategory";
        mHttpHandler = new HttpHandler.Builder(this,this,GirlTypeResultBean.class)
                .setUrl(url)
                .setResultCallBack(this)
                .build();
        mHttpHandler.getBeanData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void handleSucess(GirlTypeResultBean girlTypeResultBean) {
        List<GirlType> list = girlTypeResultBean.getData();
        LoggerUtils.d(list);
        if (list!=null&&list.size()>0){
            List<Fragment> fragments = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            for (GirlType girlType : list) {
                CommonFragment fragment = new CommonFragment();
                fragment.setGirlType(girlType.getGirlType());
                fragments.add(fragment);
                Bundle bundle = new Bundle();
                bundle.putInt("girlType",girlType.getGirlType());
                fragment.setArguments(bundle);
                titles.add(girlType.getCategoryName());
            }
            HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(),fragments,titles);
//            mTablayout.setTabsFromPagerAdapter(adapter);

            mViewPager.setAdapter(adapter);
            mViewPagerTab.setViewPager(mViewPager);
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
