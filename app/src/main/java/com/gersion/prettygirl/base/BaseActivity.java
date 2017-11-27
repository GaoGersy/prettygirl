package com.gersion.prettygirl.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;


/**
 * Created by a3266 on 2017/6/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(setLayoutId());
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    public void toActivity(Class<?> toClsActivity) {
        toActivity(toClsActivity, null);
    }

    public void toActivity(Class<?> toClsActivity, Bundle bundle) {
        Intent intent = new Intent(this, toClsActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void toActivityForResult(Class<?> toClsActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, toClsActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void toActivityForResult(Class<?> toClsActivity, int requestCode) {
        Intent intent = new Intent(this, toClsActivity);
        startActivityForResult(intent, requestCode);
    }

    public void toActivityForResult(Class<?> toClsActivity, String name, String content, int requestCode) {
        Intent intent = new Intent(this, toClsActivity);
        if (content != null) {
            intent.putExtra(name, content);
        }
        startActivityForResult(intent, requestCode);
    }

    protected <T extends View> T findView(int resId){
        T view = (T) findViewById(resId);
        return view;
    }

    public void showToast(String text){
        showToast(text, Toast.LENGTH_SHORT);
    }

    public void showToast(String text, int length){
        Toast.makeText(this,text,length).show();
    }

    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}
