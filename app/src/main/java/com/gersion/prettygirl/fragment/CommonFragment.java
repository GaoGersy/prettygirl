package com.gersion.prettygirl.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gersion.library.http.HttpHandler;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.CommonListAdapter;
import com.gersion.prettygirl.base.LazyLoadFragment;
import com.gersion.prettygirl.bean.CategoryResultBean;
import com.gersion.prettygirl.bean.GirlCategory;
import com.gersion.prettygirl.constants.AppConstants;
import com.gersion.smartrecycleviewlibrary.SmartRecycleView;
import com.gersion.smartrecycleviewlibrary.ptr2.PullToRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aa326 on 2017/11/27.
 */

public class CommonFragment extends LazyLoadFragment implements HttpHandler.ResultCallBack<CategoryResultBean> {

    private int mGirlType;
    private CommonListAdapter mAdapter;
    private String url = AppConstants.URLS.GET_CATEGORY_LIST;
    private SmartRecycleView mSmartRecycleView;
    private HttpHandler mHttpHandler;

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
        mSmartRecycleView.setLayoutManger(SmartRecycleView.LayoutManagerType.GRID_LAYOUT,LinearLayoutManager.VERTICAL );
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
        mGirlType = getArguments().getInt("girlType");

    }

    private void getData(int page) {
        mHttpHandler.setParamMap("currentPage",page);
        mHttpHandler.getBeanData();
    }

    @Override
    protected void initListener() {

    }

    public void setGirlType(int girlType) {
        mGirlType = girlType;
    }

    @Override
    public void handleSucess(CategoryResultBean categoryResultBean) {
        final List<GirlCategory> list = categoryResultBean.getData().getList();
        mSmartRecycleView.handleData(list);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    protected void initData() {
        Map<String,Object> params = new HashMap<>();
        params.put("girlType", mGirlType);
        params.put("currentPage", 1);
        params.put("pageSize", 10);
        mHttpHandler = new HttpHandler.Builder(getActivity(),this,CategoryResultBean.class)
                .setUrl(url)
                .setResultCallBack(this)
                .setParamMap(params)
                .setHttpMethod(HttpHandler.POST)
                .build();
        getData(1);
    }
}
