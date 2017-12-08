package com.gersion.prettygirl.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gersion.library.dialog.LoadingDialog;
import com.gersion.library.http.HttpHandler;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.bean.GirlImage;
import com.gersion.prettygirl.bean.GirlImageResutBean;
import com.gersion.prettygirl.constants.AppConstants;
import com.gersion.prettygirl.view.TitleView;
import com.gersion.smartrecycleviewlibrary.SmartRecycleView;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa326 on 2017/11/27.
 */

public class DetailActivity extends BaseActivity implements HttpHandler.ResultCallBack<GirlImageResutBean> {
    private PhotoPagerAdapter mAdapter;
    private String mUrl;
    private TitleView mTitleView;
    private SmartRecycleView mSmartRecycleView;

    private ViewPager mViewPager;
    private List<View> mData;
    private List<String> mImgUrls;
    private boolean mIsOpen = true;
    private LinearLayout mLlContainer;
    private int mPisition = 0;
    private TextView mTvPosition;
    private int totalCount;
    private LoadingDialog mLoadingDialog;
    private View mRlContainer;
    private int mRlContainerHeight;
    private int mTitleViewHeight;
    private HttpHandler mHttpHandler;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        mTitleView = findView(R.id.titleView);
//        mSmartRecycleView = findView(R.id.smartRecycleView);
//        mSmartRecycleView.setLayoutManger(SmartRecycleView.LayoutManagerType.LINEAR_LAYOUT);
//        mAdapter = new CommonPictureAdapter();
//        mAdapter.registerMultiBean(GirlImage.class,R.layout.item_picture_list);
////        mAdapter.registerMultiBean(Bean.DataBean.ListBean.class,R.layout.item_common_list);
//        mSmartRecycleView.setAdapter(mAdapter);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLlContainer = (LinearLayout) findViewById(R.id.ll_container);
        mTvPosition = (TextView) findViewById(R.id.tv_position);
        mRlContainer = findViewById(R.id.rl_container);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int categoryId = intent.getIntExtra("categoryId", -1);
        mTitleView.setTitleText(title);
        mUrl = AppConstants.BASE_URL + "image/getImageListByCategoryId?categoryId=" + categoryId;

        mHttpHandler = new HttpHandler.Builder(this,this,GirlImageResutBean.class)
                .setUrl(mUrl)
                .setResultCallBack(this)
                .build();
        getData();

    }

    @Override
    protected void initListener() {
//        mLlContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                saveImage(mPisition);
////                checkPremission();
//            }
//        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mPisition = position;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setPositionText(position + 1);
            }
        });
    }

    @Override
    public void handleSucess(GirlImageResutBean bean) {
        List<GirlImage> list = bean.getData();
        setData(list);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    private void getData() {
        mHttpHandler.getBeanData();
    }


//    private void checkPremission() {
//        PermissionUtils.getInstance()
//                .requestPermission(mContext, new PermissionUtils.PermissionResultCallback() {
//                    @Override
//                    public void onSuccess() {
//                        saveImage(mPisition);
//                    }
//
//                    @Override
//                    public void onFailed() {
//                        ToastUtil.show(mContext,"不能读写存储，图片将无法保存");
//                    }
//                },PermissionUtils.WRITE_EXTERNAL_STORAGE);
//    }

    private void setPositionText(int position) {
        mTvPosition.setText(position + "/" + totalCount);
    }

    public void setData(List<GirlImage> imageList) {//只要进来了就会返回true,用来处理用户连续点击的问题
        mData = new ArrayList<>();
        mImgUrls = new ArrayList<>();
        totalCount = imageList.size();
        LayoutInflater inflater = LayoutInflater.from(DetailActivity.this);
        for (final GirlImage girlImage : imageList) {
            FrameLayout photoView = (FrameLayout) inflater.inflate(R.layout.view_photo_view, null);
            String imgUrl = AppConstants.BASE_IMAGE_URL + girlImage.getImageUrl();
            mImgUrls.add(imgUrl);
            mData.add(photoView);
        }
        setPositionText(1);
        mAdapter = new PhotoPagerAdapter(mData);
        mViewPager.setAdapter(mAdapter);
        mRlContainerHeight = mRlContainer.getMeasuredHeight();
        mTitleViewHeight = mTitleView.getMeasuredHeight();
    }

//    private void saveImage(int position) {
//        mLoadingDialog = new LoadingDialog(mContext);
//        String url = mImgUrls.get(position);
//        SaveImageUtils.saveImageToGallery(mContext, url, new SaveImageUtils.ImageSaveCallBack() {
//            @Override
//            public void onSaveSuccess() {
//                mLoadingDialog.dismiss();
//                ToastUtil.show(mContext, "保存成功");
//            }
//
//            @Override
//            public void onSaveFail() {
//                mLoadingDialog.dismiss();
//                ToastUtil.show(mContext, "保存失败");
//            }
//        });
//
//    }

    private void viewUp(View view, float y) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", y, 0);
        animator.setDuration(300);
        animator.start();
        mIsOpen = true;
    }

    private void viewDown(View view, float y) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, y);
        animator.setDuration(300);
        animator.start();
        mIsOpen = false;
    }

    public class PhotoPagerAdapter extends PagerAdapter {
        private List<View> mData;

        public PhotoPagerAdapter(List<View> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mData.get(position));//删除页卡
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
            String imgUrl = mImgUrls.get(position);
            FrameLayout flContainer = (FrameLayout) mData.get(position);
            final PhotoView photoView = (PhotoView) flContainer.findViewById(R.id.photoView);
            final SpinKitView spinKitView = (SpinKitView) flContainer.findViewById(R.id.spin_kit);
            final TextView tv_notice = (TextView) flContainer.findViewById(R.id.tv_notice);
            Glide.with(DetailActivity.this).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onLoadStarted(Drawable placeholder) {
                    spinKitView.setVisibility(View.VISIBLE);
                    tv_notice.setVisibility(View.GONE);
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    spinKitView.setVisibility(View.GONE);
                    tv_notice.setVisibility(View.VISIBLE);
                }

                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    spinKitView.setVisibility(View.GONE);
                    tv_notice.setVisibility(View.GONE);
                    photoView.setImageBitmap(resource);
                }
            });

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mIsOpen) {
                        viewDown(mRlContainer, mRlContainerHeight);
                        viewDown(mTitleView, -mTitleViewHeight);
                    } else {
                        viewUp(mRlContainer, mRlContainerHeight);
                        viewUp(mTitleView, -mTitleViewHeight);
                    }
                }
            });
            container.addView(flContainer, 0);//添加页卡
            return flContainer;
        }

    }
}
