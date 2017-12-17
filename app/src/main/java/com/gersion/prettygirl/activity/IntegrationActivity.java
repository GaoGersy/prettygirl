package com.gersion.prettygirl.activity;

import android.view.View;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.view.TitleView;

public class IntegrationActivity extends BaseActivity {

    private TitleView mTitleView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_integration;
    }

    @Override
    protected void initView() {
        mTitleView = findView(R.id.titleView);
        mTitleView.setTitleText("积分");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
