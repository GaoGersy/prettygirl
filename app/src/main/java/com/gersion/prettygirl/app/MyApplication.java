package com.gersion.prettygirl.app;

import android.content.Context;

import com.gersion.library.app.BaseApplication;
import com.gersion.prettygirl.utils.RudenessScreenHelper;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by aa326 on 2017/12/7.
 */

public class MyApplication extends BaseApplication {
    static Context context;

    public static Context getContext() {

        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        new RudenessScreenHelper(this, 750).activate();//屏幕适配，初始化单位为pt
        CrashReport.initCrashReport(getApplicationContext(), "83a16f6a72", false);
        initOkHttpAndLogger();
    }
}
