package com.gersion.prettygirl.utils;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ruifeng on 2016/10/13.
 */
public  abstract class CallBack extends StringCallback {
    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {

    }
    //只有这一个方法运行在子线程

}
