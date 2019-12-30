package com.example.base.util;

import java.text.DecimalFormat;

/**
 * @author： zhangtuo
 * @date： 2019-11-12
 * @description：
 */
public class StringUtils {

    /**
     * 点赞、评论数格式,带四舍五入  整万整千万四舍五入
     */
    public static String formatVNVideoView(long viewCount) {
        if (viewCount == 0)
            return "";
        DecimalFormat df = new DecimalFormat("#");
        String result = String.valueOf(viewCount);
        if (viewCount / 100000000 >= 99) {//最大99亿
            result = "99亿";
        } else if (viewCount / 100000000 > 0) {//亿
            result = df.format(viewCount / 100000000d) + "亿";
        } else if (viewCount / 10000000 > 0) {//千万
            result = df.format(viewCount / 10000000d) + "千万";
        } else if (viewCount / 10000 > 0) {//万
            result = df.format(viewCount / 10000d) + "万";
        }
        if (result.equals("1000万")) {//处理四舍五入后为1000万的数字，显示为1千万。如9999999
            result = "1千万";
        }
        return result;
    }


    public static void test() {
        LogUtils.i(LogUtils.TAG, "3000-----------" + formatVNVideoView(3000));
        LogUtils.i(LogUtils.TAG, "5000-----------" + formatVNVideoView(5000));
        LogUtils.i(LogUtils.TAG, "6000-----------" + formatVNVideoView(6000));
        LogUtils.i(LogUtils.TAG, "9000-----------" + formatVNVideoView(9000));
        LogUtils.i(LogUtils.TAG, "9500-----------" + formatVNVideoView(9500));
        LogUtils.i(LogUtils.TAG, "9999-----------" + formatVNVideoView(9999));


        LogUtils.i(LogUtils.TAG, "10000-----------" + formatVNVideoView(10000));
        LogUtils.i(LogUtils.TAG, "10001-----------" + formatVNVideoView(10001));
        LogUtils.i(LogUtils.TAG, "13333-----------" + formatVNVideoView(13333));
        LogUtils.i(LogUtils.TAG, "14444-----------" + formatVNVideoView(14444));
        LogUtils.i(LogUtils.TAG, "14994-----------" + formatVNVideoView(14994));
        LogUtils.i(LogUtils.TAG, "14999-----------" + formatVNVideoView(14999));
        LogUtils.i(LogUtils.TAG, "15000-----------" + formatVNVideoView(15000));
        LogUtils.i(LogUtils.TAG, "15001-----------" + formatVNVideoView(15001));
        LogUtils.i(LogUtils.TAG, "15999-----------" + formatVNVideoView(15999));
        LogUtils.i(LogUtils.TAG, "19499-----------" + formatVNVideoView(19499));
        LogUtils.i(LogUtils.TAG, "19999-----------" + formatVNVideoView(19999));
        LogUtils.i(LogUtils.TAG, "14999999-----------" + formatVNVideoView(14999999));
        LogUtils.i(LogUtils.TAG, "15000000-----------" + formatVNVideoView(15000000));
        LogUtils.i(LogUtils.TAG, "15000001-----------" + formatVNVideoView(15000001));


    }
}
