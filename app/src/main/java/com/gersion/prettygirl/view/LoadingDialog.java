package com.gersion.prettygirl.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.gersion.prettygirl.R;

/**
 * Created by Administrator on 2017/2/10.
 */
public class LoadingDialog {

    private Dialog mLoadingDialog;

    public LoadingDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        // 创建自定义样式dialog
        mLoadingDialog = new Dialog(context, R.style.loading_dialog);
        try {
            mLoadingDialog.show();
        }catch (Exception e){

        }

        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setCancelable(true);// 不可以用“返回键”取消
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

    }

    public void setCancelable(boolean isCancelable) {
        mLoadingDialog.setCancelable(isCancelable);
    }

    public void dismiss() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mLoadingDialog=null;
    }
}
