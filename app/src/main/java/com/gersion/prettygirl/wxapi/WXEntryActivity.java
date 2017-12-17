package com.gersion.prettygirl.wxapi;


import com.bilibili.socialize.share.core.ui.BaseWXEntryActivity;
import com.gersion.prettygirl.constants.AppConstants;


/**
 * Created by ntop on 15/9/4.
 */
public class WXEntryActivity extends BaseWXEntryActivity {

    @Override
    protected String getAppId() {
        return AppConstants.WEIXIN_APP_ID;
    }

}

