package com.gersion.prettygirl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gersion.library.http.HttpHandler;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.PicturePagerAdapter;
import com.gersion.prettygirl.base.LazyLoadFragment;
import com.gersion.prettygirl.bean.GirlType;
import com.gersion.prettygirl.bean.GirlTypeResultBean;
import com.gersion.prettygirl.constants.AppConstants;
import com.gersion.prettygirl.utils.LoggerUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa326 on 2017/12/11.
 */

public class PictureFragment extends LazyLoadFragment implements HttpHandler.ResultCallBack<GirlTypeResultBean> {

    private ViewPager mViewPager;
    private SmartTabLayout mViewPagerTab;
    private HttpHandler mHttpHandler;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initView() {
        mViewPager = findView(R.id.viewPager);
        mViewPagerTab = findView(R.id.viewpagertab);
    }

    @Override
    protected void initData(Bundle bundle) {

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
            PicturePagerAdapter adapter = new PicturePagerAdapter(getChildFragmentManager(),fragments,titles);
//            mTablayout.setTabsFromPagerAdapter(adapter);

            mViewPager.setAdapter(adapter);
            mViewPagerTab.setViewPager(mViewPager);
            mViewPager.setOffscreenPageLimit(fragments.size()-1);
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    protected void initData() {
        String url = AppConstants.URLS.GET_ALL_IMAGE_LIST;
        mHttpHandler = new HttpHandler.Builder(getActivity(),this,GirlTypeResultBean.class)
                .setUrl(url)
                .setResultCallBack(this)
                .build();
        mHttpHandler.getBeanData();
    }
}
