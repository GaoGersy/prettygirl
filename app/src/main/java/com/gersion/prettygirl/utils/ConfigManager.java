package com.gersion.prettygirl.utils;

import android.content.Context;

import com.gersion.prettygirl.app.MyApplication;

/**
 * Created by gersy on 2017/7/1.
 */

public class ConfigManager {
    private Context mContext;

    private ConfigManager() {
        mContext = MyApplication.getContext();
    }
    private boolean getBoolean(String key) {
        return SpfUtils.getBoolean(mContext, key,false);
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        return SpfUtils.getBoolean(mContext, key, defaultValue);
    }

    private static ConfigManager singleInstance = new ConfigManager();

    public static ConfigManager getInstance() {
        return singleInstance;
    }

}
