package com.gersion.prettygirl.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * @author liufuning
 *         <p>
 *         create time:2013-11-14上午9:24:12
 */
public final class IntentUtil {

    /**
     * Activity之间跳转
     *
     * @param activity
     * @param cls
     */
    public static void startActivity(Activity activity, Class<?> cls) {
        startActivity(activity, cls, null);
    }

    /**
     * Activity之间跳转
     *
     * @param activity
     * @param cls
     * @param extras
     */
    public static void startActivity(Activity activity, Class<?> cls,
                                     Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);

        if (extras != null) {
            intent.putExtras(extras);
        }

        activity.startActivity(intent);
    }

    /**
     * Activity之间跳转
     *
     * @param activity
     * @param cls
     * @param extras
     */
    public static void startActivityForResult(Activity activity, Class<?> cls,
                                              int requestCode, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);

        if (extras != null) {
            intent.putExtras(extras);
        }

        activity.startActivityForResult(intent, requestCode);
    }

}
