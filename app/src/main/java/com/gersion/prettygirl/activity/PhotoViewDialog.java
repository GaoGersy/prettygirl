//package com.gersion.prettygirl.activity;
//
//import com.google.gson.Gson;
//
//import android.animation.ObjectAnimator;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.animation.AnticipateInterpolator;
//import android.view.animation.OvershootInterpolator;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.github.ybq.android.spinkit.SpinKitView;
//import com.jojo.sns.R;
//import com.jojo.sns.base.BaseAlertDialog;
//import com.jojo.sns.http.Constants;
//import com.jojo.sns.utils.PermissionUtils;
//import com.jojo.sns.utils.SaveImageUtils;
//import com.jojo.sns.utils.ToastUtil;
//
//import uk.co.senab.photoview.PhotoView;
//import uk.co.senab.photoview.PhotoViewAttacher;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by Administrator on 2017/2/24.
// */
//public class PhotoViewDialog extends BaseAlertDialog {
//
//    private ViewPager mViewPager;
//    private PhotoPagerAdapter mAdapter;
//    private List<View> mData;
//    private List<String> mImgUrls;
//    private boolean mIsOpen = true;
//    private LinearLayout mLlContainer;
//    private int mPisition = 0;
//    private TextView mTvPosition;
//    private int totalCount;
//    private LoadingDialog mLoadingDialog;
//
//    public PhotoViewDialog(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.dialog_photo_show;
//    }
//
//    @Override
//    protected void initView() {
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mLlContainer = (LinearLayout) findViewById(R.id.ll_container);
//        mTvPosition = (TextView) findViewById(R.id.tv_position);
//        mDialog.setCancelable(true);
//        WindowManager.LayoutParams layoutParams = mWindow.getAttributes();
//        layoutParams.gravity = Gravity.CENTER;
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        mLlContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                saveImage(mPisition);
//                checkPremission();
//            }
//        });
//
//        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                mPisition = position;
//            }
//        });
//
//        mWindow.getDecorView().setPadding(0, 0, 0, 0);
//
//        mWindow.setAttributes(layoutParams);
//
//        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                setPositionText(position + 1);
//            }
//        });
//
//    }
//
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
//
//    private void setPositionText(int position) {
//        mTvPosition.setText(position + "/" + totalCount);
//    }
//
//    @Override
//    protected void initListener() {
//
//    }
//
//    public void setData(String data) {//只要进来了就会返回true,用来处理用户连续点击的问题
//        Gson gson = new Gson();
//        UrlBean urlBean = gson.fromJson(data, UrlBean.class);
//        if (urlBean.Urls == null) {
//            return;
//        }
//        mData = new ArrayList<>();
//        mImgUrls = new ArrayList<>();
//        totalCount = urlBean.Urls.size();
//        setPositionText(urlBean.index + 1);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        for (final String url : urlBean.Urls) {
//            FrameLayout photoView = (FrameLayout) inflater.inflate(R.layout.view_photo_view, null);
//            String imgUrl = url;
//            if (!url.startsWith("http")) {
//                imgUrl = Constants.IMGSCHEME + url;
//            }
//            mImgUrls.add(imgUrl);
//            mData.add(photoView);
//        }
//        mAdapter = new PhotoPagerAdapter(mData);
//        mViewPager.setAdapter(mAdapter);
//        mViewPager.setCurrentItem(urlBean.index);
//    }
//
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
//
//    @Override
//    public void dismiss() {
//        if (mLoadingDialog != null) {
//            mLoadingDialog.dismiss();
//        }
//        super.dismiss();
//    }
//
//    private void open(View view, float y) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", y, 0);
//        animator.setDuration(500);
//        animator.setInterpolator(new OvershootInterpolator());
//        animator.start();
//        mIsOpen = true;
//    }
//
//    private void close(View view, float y) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, y);
//        animator.setDuration(500);
//        animator.setInterpolator(new AnticipateInterpolator());
//        animator.start();
//        mIsOpen = false;
//    }
//
//    class UrlBean {
//        public int index;
//        public List<String> Urls;
//    }
//
//    public void setOnDissmissListener(DialogInterface.OnDismissListener listener) {
//        mDialog.setOnDismissListener(listener);
//    }
//
//    public class PhotoPagerAdapter extends PagerAdapter {
//        private List<View> mData;
//
//        public PhotoPagerAdapter(List<View> data) {
//            mData = data;
//        }
//
//        @Override
//        public int getCount() {
//            return mData == null ? 0 : mData.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mData.get(position));//删除页卡
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
//            String imgUrl = mImgUrls.get(position);
//            FrameLayout flContainer = (FrameLayout) mData.get(position);
//            final PhotoView photoView = (PhotoView) flContainer.findViewById(R.id.photoView);
//            final SpinKitView spinKitView = (SpinKitView) flContainer.findViewById(R.id.spin_kit);
//            final TextView tv_notice = (TextView) flContainer.findViewById(R.id.tv_notice);
//            Glide.with(mContext).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onLoadStarted(Drawable placeholder) {
//                    spinKitView.setVisibility(View.VISIBLE);
//                    tv_notice.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                    spinKitView.setVisibility(View.GONE);
//                    tv_notice.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    spinKitView.setVisibility(View.GONE);
//                    tv_notice.setVisibility(View.GONE);
//                    photoView.setImageBitmap(resource);
//                }
//            });
//
//            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
//                @Override
//                public void onViewTap(View view, float x, float y) {
//                    dismiss();
//                }
//            });
//            container.addView(flContainer, 0);//添加页卡
//            return flContainer;
//        }
//
//    }
//
//}
