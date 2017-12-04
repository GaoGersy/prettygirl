package com.gersion.prettygirl.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.constants.AppConstants;

public class ImageUtil {

    // 返回合成图片
    public static Bitmap doodle(Bitmap src, Bitmap watermark, float x, float y, float left, float top) throws OutOfMemoryError {
        float scaleX = (float) src.getWidth() / x;
        float scaleY = (float) src.getHeight() / y;

        // 另外创建一张图片
        // 创建一个新的和SRC长度宽度一样的位图
        Bitmap newb = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Config.ARGB_8888); // FIXME
        // 耗费内存
        Canvas canvas = new Canvas(newb);
        canvas.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入原图片src
        float martLeft = left * scaleX;
        float markTop = top * scaleY;
        canvas.drawBitmap(watermark, martLeft, markTop, null);
        return newb;
    }


//    public static void loadNoCircleImage(Context context, String url, ImageView imag) {
//        Glide
//                .with(context)
//                .load(url)
//                .placeholder(R.mipmap.photo_default) //设置占位图
//                .error(R.mipmap.photo_default) //设置错误图片
//                .crossFade().into(imag);//设置淡入淡出效果，默认300ms，可以传参
//    }
//
//    public static void loadNetImage(Context context, String url, ImageView imag) {
//        Glide
//                .with(context)
//                .load(url).transform(new CircleTransform(BaseApplication.getInstance()))
//                .placeholder(R.drawable.photo_default) //设置占位图
//                .error(R.drawable.photo_default) //设置错误图片
//                .crossFade(200) //设置淡入淡出效果，默认300ms，可以传参
//                //.dontAnimate() //不显示动画效果
//                .into(imag);
//    }
//
//    public static void loadRetangleImage(Context context, String url, ImageView imag) {
//        Glide
//                .with(context)
//                .load(url)
//                .placeholder(R.mipmap.photo_default) //设置占位图
//                .error(R.mipmap.photo_default) //设置错误图片
//                .crossFade(200) //设置淡入淡出效果，默认300ms，可以传参
//                //.dontAnimate() //不显示动画效果
//                .into(imag);
//    }
//
//
//    //只用传入服务器传回来的图片后面的地址，不用加上前缀
//    public static void loadCircleImage(Context context, String url, ImageView imag) {
//        if (Util.isOnMainThread()) {
//            if (context != null && !((Activity) context).isFinishing()) {
//                Glide
//                        .with(context)
//                        .load(Constants.IMGSCHEME + url).transform(new CircleTransform(BaseApplication.getInstance()))
//                        .placeholder(R.mipmap.default_user) //设置占位图
//                        .error(R.mipmap.default_user) //设置错误图片
//                        .crossFade() //设置淡入淡出效果，默认300ms，可以传参
//                        .into(imag);
//            }
//        }
//    }

    //解决第一次只加载默认图片
    public static void loadCircleNoLoad(Context context, String url, final ImageView imag, int imgId) {
        Glide
                .with(context)
                .load(AppConstants.BASE_IMAGE_URL + url)
                .placeholder(imgId) //设置占位图
                .error(imgId) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imag.setImageDrawable(resource);
                    }
                });
    }

    //解决第一次只加载默认图片
    public static void loadImage(Context context, String url, final ImageView imag) {
        loadCircleNoLoad(context,url,imag,R.mipmap.photo_default);
    }

//    public static DrawableRequestBuilder<String> getInstance(Context context, String url) {
//        DrawableRequestBuilder<String> builder = Glide
//                .with(context)
//                .load(url).transform(new CircleTransform(BaseApplication.getInstance()));
//        return builder;
//    }

}
