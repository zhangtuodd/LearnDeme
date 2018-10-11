package com.example.base.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;


import com.example.base.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;



/**
 * Glide
 *
 * @author zhangtuo
 * @date 2018/3/22
 */

public class GlideUtils {
    /**
     * @param context 上下文
     * @param iv
     * @param url
     * @param place   占位图
     */
    public static void get(Context context, ImageView iv, String url, int place) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(place)
                .error(place)
                .into(iv);
    }

    /**
     * 默认占位图
     *
     * @param context 上下文
     * @param iv
     * @param url
     */
    public static void get(Context context, ImageView iv, String url) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .centerCrop()
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .into(iv);
    }

    /**
     * 默认占位图
     *
     * @param context 上下文
     * @param iv
     * @param url
     */
    public static void get(Context context, ImageView iv, Uri url) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .centerCrop()
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .into(iv);
    }

    /**
     * 加载资源图片
     * centerCrop()固定宽高
     *
     * @param context 上下文
     * @param iv
     * @param url
     */
    public static void getResource(Context context, ImageView iv, @DrawableRes int url) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .into(iv);
    }


    /**
     * 全圆角图片
     *
     * @param context
     * @param iv
     * @param url     图片地址
     * @param dp      圆角半径
     */
    public static void getRound(Context context, final ImageView iv, String url, final int dp) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .transform(new RoundedCornersTransformation(SizeUtils.dip2px(context,dp), 0, RoundedCornersTransformation.CornerType.ALL))
                .into(iv);
    }

    /**
     * 全圆角图片
     *
     * @param context
     * @param iv
     * @param url     图片地址
     * @param dp      圆角半径
     */
    public static void getCircleByte(Context context, final ImageView iv, byte[] url, int place) {
        GlideApp.with(context)
                .load(url)
                .circleCrop()
                .placeholder(place)
                .error(place)
                .into(iv);
    }
    /**
     * 顶部圆角图片
     *
     * @param context
     * @param iv
     * @param url     图片地址
     * @param dp      圆角半径
     */
    public static void getRoundTop(Context context, final ImageView iv, String url, final int dp) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .transform(new RoundedCornersTransformation(SizeUtils.dip2px(context,dp), 0, RoundedCornersTransformation.CornerType.TOP))
                .into(iv);
    }

    /**
     * 底部圆角图片
     *
     * @param context
     * @param iv
     * @param url     图片地址
     * @param dp      圆角半径
     */
    public static void getRoundBottom(Context context, final ImageView iv, String url, final int dp) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .transform(new RoundedCornersTransformation(SizeUtils.dip2px(context,dp), 0, RoundedCornersTransformation.CornerType.BOTTOM))
                .into(iv);
    }

    /**
     * 圆图片  默认占位图
     *
     * @param context
     * @param iv
     * @param url     图片地址
     */
    public static void getCircle(Context context, final ImageView iv, String url) {
        GlideApp.with(context)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .into(iv);
    }

    /**
     * 圆形图片 特殊占位图
     *
     * @param context
     * @param iv
     * @param url     图片地址
     */
    public static void getCircle(Context context, final ImageView iv, String url, int place) {
        GlideApp.with(context)
                .load(url)
                .circleCrop()
                .placeholder(place)
                .error(place)
                .into(iv);
    }

    /**
     * 固定宽高大小
     *
     * @param context
     * @param iv
     * @param url     图片地址
     */
    public static void getFixedSize(Context context, final ImageView iv, String url, int w, int h) {
        GlideApp.with(context)
                .load(url)
                .override(w, h)
                .placeholder(R.drawable.common_ic_holder)
                .error(R.drawable.common_ic_holder)
                .into(iv);
    }
}
