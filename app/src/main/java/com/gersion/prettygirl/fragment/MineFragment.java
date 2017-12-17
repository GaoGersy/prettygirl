package com.gersion.prettygirl.fragment;

import android.os.Bundle;
import android.view.View;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.activity.IntegrationActivity;
import com.gersion.prettygirl.base.BaseFragment;
import com.gersion.prettygirl.view.ItemView;

/**
 * Created by aa326 on 2017/12/11.
 */

public class MineFragment extends BaseFragment {

    private ItemView mIntegration;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        mIntegration = findView(R.id.integration);
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initListener() {
        mIntegration.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(IntegrationActivity.class);
            }
        });
    }
}
