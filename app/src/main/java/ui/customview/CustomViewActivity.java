package ui.customview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by zhangtuo on 2017/10/17.
 */

public class CustomViewActivity extends Activity {

    CircleBarView circleBarView;
    TextView circleNum;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        circleBarView = (CircleBarView) findViewById(R.id.circle_bar_view);
//        circleNum = (TextView) findViewById(R.id.tv);
        circleBarView.setTextView(circleNum);
        //
        circleBarView.setProgressNum(0, 3000);
//        DecimalFormat formater = new DecimalFormat();
//        formater.setMaximumFractionDigits(1);
//        formater.setMinimumFractionDigits(1);
//        formater.setGroupingSize(0);//分组大小是数的整数部分中分组分隔符之间的数字位数。例如在数 "123,456.78" 中，分组大小是 3。
//        formater.setRoundingMode(RoundingMode.FLOOR);
//        LogUtils.i("CustomViewActivity", "123456-----------" + 123456/10000f);
//        LogUtils.i("CustomViewActivity", "456789-----------" + 456789/10000f);

        textView = findViewById(R.id.tv1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("分组大小是数的整数部分中分组分隔符之间的数字位数");
                    }
                });
            }
        },2000);

        LogUtils.i("CustomViewActivity", "2222-----------" + formatLikeAndCommentNum(2222));
        LogUtils.i("CustomViewActivity", "5555-----------" + formatLikeAndCommentNum(5555));
        LogUtils.i("CustomViewActivity", "8888-----------" + formatLikeAndCommentNum(8888));
        LogUtils.i("CustomViewActivity", "9999-----------" + formatLikeAndCommentNum(9999));
        LogUtils.i("CustomViewActivity", "10000-----------" + formatLikeAndCommentNum(10000));
        LogUtils.i("CustomViewActivity", "44444-----------" + formatLikeAndCommentNum(44444));
        LogUtils.i("CustomViewActivity", "55555-----------" + formatLikeAndCommentNum(55555));
        LogUtils.i("CustomViewActivity", "99999-----------" + formatLikeAndCommentNum(99999));
        LogUtils.i("CustomViewActivity", "100000-----------" + formatLikeAndCommentNum(100000));
        LogUtils.i("CustomViewActivity", "100009-----------" + formatLikeAndCommentNum(100009));
        LogUtils.i("CustomViewActivity", "111111-----------" + formatLikeAndCommentNum(111111));
        LogUtils.i("CustomViewActivity", "222222-----------" + formatLikeAndCommentNum(222222));
        LogUtils.i("CustomViewActivity", "555555-----------" + formatLikeAndCommentNum(555555));
        LogUtils.i("CustomViewActivity", "999999-----------" + formatLikeAndCommentNum(999999));
        LogUtils.i("CustomViewActivity", "1000000-----------" + formatLikeAndCommentNum(1000000));
        LogUtils.i("CustomViewActivity", "1000099-----------" + formatLikeAndCommentNum(1000099));
        LogUtils.i("CustomViewActivity", "1000999-----------" + formatLikeAndCommentNum(1000999));
        LogUtils.i("CustomViewActivity", "1099999-----------" + formatLikeAndCommentNum(1099999));
        LogUtils.i("CustomViewActivity", "1999999-----------" + formatLikeAndCommentNum(1999999));
        LogUtils.i("CustomViewActivity", "5555555-----------" + formatLikeAndCommentNum(5555555));
        LogUtils.i("CustomViewActivity", "6666666-----------" + formatLikeAndCommentNum(6666666));
        LogUtils.i("CustomViewActivity", "6999999-----------" + formatLikeAndCommentNum(6999999));
        LogUtils.i("CustomViewActivity", "9999999-----------" + formatLikeAndCommentNum(9999999));
        LogUtils.i("CustomViewActivity", "10000000-----------" + formatLikeAndCommentNum(10000000));
        LogUtils.i("CustomViewActivity", "55555555-----------" + formatLikeAndCommentNum(55555555));
        LogUtils.i("CustomViewActivity", "66666666-----------" + formatLikeAndCommentNum(66666666));
        LogUtils.i("CustomViewActivity", "66669999-----------" + formatLikeAndCommentNum(66669999));
        LogUtils.i("CustomViewActivity", "59999999/10000-----------" + 59999999/10000f);
        LogUtils.i("CustomViewActivity", "59999999-----------" + formatLikeAndCommentNum(59999999));
        LogUtils.i("CustomViewActivity", "99999999-----------" + formatLikeAndCommentNum(99999999));
        LogUtils.i("CustomViewActivity", "100000000-----------" + formatLikeAndCommentNum(100000000));
        LogUtils.i("CustomViewActivity", "155555555-----------" + formatLikeAndCommentNum(155555555));
        LogUtils.i("CustomViewActivity", "999999999-----------" + formatLikeAndCommentNum(999999999));
        LogUtils.i("CustomViewActivity", "1000000000-----------" + formatLikeAndCommentNum(1000000000));



    }
    public static String formatLikeAndCommentNum(long viewCount){
        if(viewCount <= 0){
            return "";
        }
        String result = String.valueOf(viewCount);
        if(viewCount <10000){//0 < * < 1万
            return result;
        }

        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(1);//小数位最高一位
        formater.setMinimumFractionDigits(1);//小数位最低一位
        formater.setGroupingSize(0);//分组大小是数的整数部分中分组分隔符之间的数字位数。例如在数 "123,456.78" 中，分组大小是 3。
        formater.setRoundingMode(RoundingMode.DOWN);//正数不四舍五入

        if(viewCount < 100000000){//1万 <= * <1亿
            return formater.format(viewCount / 10000d) + "万";
        }
        return formater.format(viewCount / 100000000d) + "亿";// * >= 1亿
    }


    public static String formatVNVideoView(long viewCount) {
        if (viewCount == 0)
            return "";
        DecimalFormat df = new DecimalFormat("#");
        String result = String.valueOf(viewCount);
        if (viewCount / 100000000 >= 99) {//最大99亿
            result = "99亿";
        } else if (viewCount / 100000000 > 0) {//亿
            result = df.format(viewCount / 100000000d) + "亿";
        } else if (viewCount/ 10000000 > 0){//千万
            result = df.format(viewCount / 10000000d) + "千万";
        } else if (viewCount / 10000 > 0) {//万
            result = df.format(viewCount / 10000d) + "万";
        }
        if (result.equals("1000万")){//处理四舍五入后为1000万的数字，显示为1千万。如9999999
            result = "1千万";
        }
        return result;
    }


}
