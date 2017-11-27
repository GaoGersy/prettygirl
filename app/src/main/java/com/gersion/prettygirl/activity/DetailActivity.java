package com.gersion.prettygirl.activity;

import android.content.Intent;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.CommonPictureAdapter;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.bean.HomeListBean;
import com.gersion.prettygirl.utils.GsonQuick;
import com.gersion.prettygirl.utils.LoggerUtils;
import com.gersion.prettygirl.view.TitleView;
import com.gersion.smartrecycleviewlibrary.SmartRecycleView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by aa326 on 2017/11/27.
 */

public class DetailActivity extends BaseActivity {
    private CommonPictureAdapter mAdapter;
    private String mUrl;
    private TitleView mTitleView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        mTitleView = findView(R.id.titleView);
        SmartRecycleView smartRecycleView = findView(R.id.smartRecycleView);


        mAdapter = new CommonPictureAdapter();
//        mAdapter.registerMultiBean(Bean.DataBean.ListBean.class,R.layout.item_common_list);
        smartRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        mTitleView.setTitleText(title);
        if(mUrl!=null) {
            getData();
        }else {
            showToast("url 不能为空");
        }
    }

    @Override
    protected void initListener() {

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
                        return GsonQuick.fromJsontoBean(result, HomeListBean.class);
                    }
                });
    }
}
