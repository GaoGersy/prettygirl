package com.gersion.prettygirl.app;

import android.content.Context;
import android.os.Environment;

import com.gersion.library.app.BaseApplication;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.activity.MainActivity;
import com.gersion.prettygirl.utils.RudenessScreenHelper;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;


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
        initOkHttpAndLogger();
        initBugly();
    }

    private void initBugly(){

        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = false;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 60 * 1000;
        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 10 * 1000;
        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源;
         */
        Beta.largeIconId = R.mipmap.ic_launcher;
        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher;
        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher;
        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        /**
         * 已经确认过的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);

        /***** Bugly高级设置 *****/
        BuglyStrategy strategy = new BuglyStrategy();

        /***** 统一初始化Bugly产品，包含Beta *****/
        Bugly.init(this, "8f9e64bd04", true, strategy);
    }
}
