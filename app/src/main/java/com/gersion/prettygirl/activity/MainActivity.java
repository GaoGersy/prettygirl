package com.gersion.prettygirl.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.HomePagerAdapter;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.fragment.CommonFragment;
import com.gersion.prettygirl.view.TitleView;

import java.util.ArrayList;
import java.util.List;

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

        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CommonFragment fragment = new CommonFragment();
            fragment.setGirlType(1);
            fragments.add(fragment);
            titles.add("清纯美少女");
        }
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), fragments, titles);
        mTablayout.setTabsFromPagerAdapter(adapter);
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void initData() {
//        String url = AppConstants.BASE_URL+"image/getAllImageCategory";
//        OkGo.<GirlTypeResultBean>get(url)
//                .tag(this)
//                .execute(new AbsCallback<GirlTypeResultBean>() {
//                    @Override
//                    public void onSuccess(Response<GirlTypeResultBean> response) {
//                        GirlTypeResultBean data = response.body();
//                        List<GirlType> list = data.getData();
//                        LoggerUtils.d(list);
//                        if (list!=null&&list.size()>0){
//                            List<Fragment> fragments = new ArrayList<>();
//                            List<String> titles = new ArrayList<>();
//                            for (GirlType girlType : list) {
//                                CommonFragment fragment = new CommonFragment();
//                                fragment.setGirlType(girlType.getGirlType());
//                                fragments.add(fragment);
//                                titles.add(girlType.getCategoryName());
//                            }
//                            HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(),fragments,titles);
//                            mTablayout.setTabsFromPagerAdapter(adapter);
//                            mViewPager.setAdapter(adapter);
//                        }
//                    }
//
//                    @Override
//                    public GirlTypeResultBean convertResponse(okhttp3.Response response) throws Throwable {
//                        String result = response.body().string();
//                        LoggerUtils.d(result);
//                        GirlTypeResultBean baseResultBean = GsonQuick.fromJsontoBean(result, GirlTypeResultBean.class);
//                        LoggerUtils.d(baseResultBean);
//                        return baseResultBean;
//                    }
//
//                    @Override
//                    public void onError(Response<GirlTypeResultBean> response) {
//                        super.onError(response);
//                        LoggerUtils.e(response.body());
//                    }
//                });
    }

    @Override
    protected void initListener() {

    }
}
