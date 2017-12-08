package com.gersion.prettygirl.app;

import com.gersion.library.app.BaseApplication;

/**
 * Created by aa326 on 2017/12/7.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpAndLogger();
    }
}
