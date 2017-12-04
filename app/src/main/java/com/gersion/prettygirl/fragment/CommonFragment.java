package com.gersion.prettygirl.fragment;

import android.os.Bundle;

import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.CommonListAdapter;
import com.gersion.prettygirl.base.BaseFragment;
import com.gersion.prettygirl.bean.CategoryResultBean;
import com.gersion.prettygirl.bean.GirlCategory;
import com.gersion.prettygirl.bean.HomeListBean;
import com.gersion.prettygirl.constants.AppConstants;
import com.gersion.prettygirl.utils.CallBack;
import com.gersion.prettygirl.utils.GsonQuick;
import com.gersion.prettygirl.utils.LoggerUtils;
import com.gersion.smartrecycleviewlibrary.SmartRecycleView;
import com.gersion.smartrecycleviewlibrary.ptr2.PullToRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by aa326 on 2017/11/27.
 */

public class CommonFragment extends BaseFragment {

    private int mGirlType;
    private CommonListAdapter mAdapter;
    String url = AppConstants.BASE_URL + "image/getCategoryListByGirlType";
    private SmartRecycleView mSmartRecycleView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        mSmartRecycleView = findView(R.id.smartRecycleView);
        mAdapter = new CommonListAdapter();
        mAdapter.registerMultiBean(GirlCategory.class,R.layout.item_common_list);
        mSmartRecycleView.setAdapter(mAdapter);
        mSmartRecycleView.setLayoutManger(SmartRecycleView.LayoutManagerType.LINEAR_LAYOUT);
        mSmartRecycleView.refreshEnable(true)
                .loadMoreEnable(true)
                .setFirstPage(1)
                .setPageSize(10)
                .setRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh(int i) {
                        getData(i);
                    }

                    @Override
                    public void onLoadMore(int i) {
                        getData(i);
                    }
                });
    }

    @Override
    protected void initData(Bundle bundle) {
        getData(1);
    }

    private void getData(int page) {
        Map<String,String> params = new HashMap<>();
        params.put("pictureType", "1");
        params.put("currentPage", page+"");
        params.put("pageSize", "10");
        OkHttpUtils.get()
                .url(url)
//                .tag(this).params(params)
                .build()
                .execute(new CallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoggerUtils.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoggerUtils.d(response);
                        CategoryResultBean data = GsonQuick.fromJsontoBean(response, HomeListBean.class);
                        final List<GirlCategory> list = data.getData().getList();
                        mSmartRecycleView.handleData(list);
//                        mAdapter.getData().addAll(list);
//                        mAdapter.notifyDataSetChanged();
                    }
                });
//        OkGo.<HomeListBean>post(url)
//                .tag(this)
//                .params(params)
////                .params("pictureType", mGirlType)
////                .params("currentPage", page)
////                .params("pageSize", 10)
//                .execute(new AbsCallback<HomeListBean>() {
//                    @Override
//                    public void onSuccess(Response<HomeListBean> response) {
//                        GirlImageResutBean data = response.body();
//                        List<GirlImage> list = data.getData().getList();
////                        mAdapter.getItems().addAll(list);
//                        mSmartRecycleView.handleData(list);
////                        page++;
////                        if (page>10){
////                            mAdapter.notifyDataSetChanged();
////                            return;
////                        }
////                        getData();
//                    }
//
//                    @Override
//                    public HomeListBean convertResponse(okhttp3.Response response) throws Throwable {
//                        String result = response.body().string();
//                        LoggerUtils.d(result);
//                        return GsonQuick.fromJsontoBean(result, HomeListBean.class);
//                    }
//                });
    }

    @Override
    protected void initListener() {

    }

    public void setGirlType(int girlType) {
        mGirlType = girlType;
    }

}
