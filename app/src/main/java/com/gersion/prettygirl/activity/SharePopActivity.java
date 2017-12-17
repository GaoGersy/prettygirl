package com.gersion.prettygirl.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.bilibili.socialize.share.core.BiliShare;
import com.bilibili.socialize.share.core.BiliShareConfiguration;
import com.bilibili.socialize.share.core.SocializeListeners;
import com.bilibili.socialize.share.core.SocializeMedia;
import com.bilibili.socialize.share.core.error.BiliShareStatusCode;
import com.bilibili.socialize.share.core.shareparam.ShareImage;
import com.bilibili.socialize.share.core.shareparam.ShareParamWebPage;
import com.gersion.library.utils.ToastUtils;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.bean.ShareParamBean;
import com.gersion.prettygirl.constants.AppConstants;
import com.gersion.prettygirl.utils.IntentUtil;
import com.gersion.prettygirl.utils.ShareGlideImageDownloader;


public class SharePopActivity extends FragmentActivity implements OnClickListener {
    private static final String DATA_PARAM = "data_param";
    private ImageView share_weixin;
    private ImageView share_friends;
    private ImageView share_qq;

    private Bundle bundle;
    private FragmentActivity context;
    private ShareParamWebPage param;
    private BiliShare mBiliShare;
    private ImageView shareQQZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_share);
        initShare();
        context = this;

        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(DATA_PARAM)) {
                ShareParamBean paramBean = (ShareParamBean) bundle.getSerializable(DATA_PARAM);
                setParmas(paramBean);
            }
        }

        // 占满屏
        LayoutParams lay = getWindow().getAttributes();
        lay.height = LayoutParams.MATCH_PARENT;
        lay.width = LayoutParams.MATCH_PARENT;

        share_weixin = (ImageView) this.findViewById(R.id.share_weixin);
        share_friends = (ImageView) this.findViewById(R.id.share_friends);
        share_qq = (ImageView) this.findViewById(R.id.share_qq);
        shareQQZone = (ImageView) this.findViewById(R.id.share_qqZone);
        // 添加按钮监听
        share_weixin.setOnClickListener(this);
        share_friends.setOnClickListener(this);
        share_qq.setOnClickListener(this);
        shareQQZone.setOnClickListener(this);

    }

    public static void startPopShare(BaseActivity activity, ShareParamBean paramBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_PARAM, paramBean);
        IntentUtil.startActivity(activity, SharePopActivity.class, bundle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onBackPressed();
        return true;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.share_weixin) {
            startShare(SocializeMedia.WEIXIN);
        } else if (id == R.id.share_friends) {
            startShare(SocializeMedia.WEIXIN_MONMENT);
        } else if (id == R.id.share_qq) {
            startShare(SocializeMedia.QQ);
        } else if (id == R.id.share_qqZone) {//分享到QQ空间
            startShare(SocializeMedia.QZONE);
        }
    }

    private void initShare() {
        BiliShareConfiguration configuration = new BiliShareConfiguration.Builder(this)
                .imageDownloader(new ShareGlideImageDownloader())
                .qq(AppConstants.QQ_APP_ID)
                .weixin(AppConstants.WEIXIN_APP_ID)
                .build();
        mBiliShare = BiliShare.global();
        mBiliShare.config(configuration);
    }

    private void setParmas(ShareParamBean bean) {
        param = new ShareParamWebPage(bean.title, bean.content, bean.url);
        param.setThumb(new ShareImage(bean.iconUrl));
    }

    public void startShare(SocializeMedia media) {
        if (param == null) {
            return;
        }
        mBiliShare.share(this, media, param, shareListener);
    }

    protected SocializeListeners.ShareListener shareListener = new SocializeListeners.ShareListenerAdapter() {
        @Override
        public void onStart(SocializeMedia type) {
        }

        @Override
        protected void onComplete(SocializeMedia type, int code, Throwable error) {
            if (code == BiliShareStatusCode.ST_CODE_SUCCESSED) {
                ToastUtils.show(SharePopActivity.this, "分享成功");
            } else if (code == BiliShareStatusCode.ST_CODE_ERROR) {
                ToastUtils.show(SharePopActivity.this, "分享失败");
            }
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.push_bottom_out);
    }
}

