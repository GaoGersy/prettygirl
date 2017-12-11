package com.gersion.prettygirl.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class LazyLoadFragment extends BaseFragment {

    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad = true;
    protected LayoutInflater inflater;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        this.inflater = inflater;
        this.isFirstLoad = true;
        this.isPrepared = true;
        this.lazyLoad();
        return view;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.getUserVisibleHint()) {
            this.isVisible = true;
            this.onVisible();
        } else {
            this.isVisible = false;
            this.onInvisible();
        }

    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            this.isVisible = true;
            this.onVisible();
        } else {
            this.isVisible = false;
            this.onInvisible();
        }

    }

    protected void onVisible() {
        this.lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if(this.isPrepared && this.isVisible && this.isFirstLoad) {
            this.isFirstLoad = false;
            this.initData();
        }
    }

    protected abstract void initData();

}