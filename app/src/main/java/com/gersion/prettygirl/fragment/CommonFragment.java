package com.gersion.prettygirl.fragment;

import android.os.Bundle;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.CommonListAdapter;
import com.gersion.prettygirl.base.BaseFragment;
import com.gersion.prettygirl.bean.HomeListBean;
import com.gersion.prettygirl.utils.GsonQuick;
import com.gersion.prettygirl.utils.LoggerUtils;
import com.gersion.smartrecycleviewlibrary.SmartRecycleView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by aa326 on 2017/11/27.
 */

public class CommonFragment extends BaseFragment {
    private String mUrl;
    private CommonListAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        SmartRecycleView smartRecycleView = findView(R.id.smartRecycleView);
        mAdapter = new CommonListAdapter();
//        mAdapter.registerMultiBean(Bean.DataBean.ListBean.class,R.layout.item_common_list);
        smartRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle bundle) {
        getData();
    }

    private void getData() {
        OkGo.<HomeListBean>get(mUrl)
                .tag(this)
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new AbsCallback<HomeListBean>() {
                    @Override
                    public void onSuccess(Response<HomeListBean> response) {
//                        Bean.DataBean data = response.body();
//                        List<Bean.DataBean.ListBean> list = data.getList();
//                        mAdapter.getItems().addAll(list);
//                        page++;
//                        if (page>10){
//                            mAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                        getData();
                    }

                    @Override
                    public HomeListBean convertResponse(okhttp3.Response response) throws Throwable {
                        String result = response.body().string();
                        LoggerUtils.d(result);
                        return GsonQuick.fromJsontoBean(result,HomeListBean.class);
                    }
                });
    }

    @Override
    protected void initListener() {

    }

    public void setUrl(String url){
        mUrl = url;
    }

}
