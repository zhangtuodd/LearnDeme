package ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangtuo.learndeme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 一行四分 的 流失布局
 *
 * @author zhangtuo
 * @date 2018/5/10
 */

public class FlowLayout extends ViewGroup {

    List<String> list = new ArrayList<>();
    Context context;
    int screenWidth;
    int perHeight;//每个块的高度
    int perWidth;
    boolean isSingleCheck = true;//模块单选/多选

    int yetWidth;//已经用的宽度
    int parentHeight;//父view的高度

    int realWidth;//实际测量时的精确宽度
    int margin;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //真正一屏显示的宽度（除去两边的padding）
        screenWidth = wm.getDefaultDisplay().getWidth() - dp2px(context, 10);
        perWidth = (screenWidth - dp2px(context, 50)) / 4;
        perHeight = dp2px(context, 30);
        margin = dp2px(context, 5);
        this.setBackgroundColor(getResources().getColor(R.color.bangumi_index_yellow_bg));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount > 0) {
            //如果有child，默认父view高度为一块
            parentHeight = perHeight + 2 * margin;
        }
        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (yetWidth + 2 * margin + child.getMeasuredWidth() > screenWidth) {
                //另起下一行，已经占用清零
                yetWidth = 0;
                //高在原来基础上在加一块
                parentHeight = parentHeight + perHeight + 2 * margin;
            } else {
                yetWidth = yetWidth + perWidth + 2 * margin;
            }
        }
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : screenWidth,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : parentHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        yetWidth = 0;
        if (childCount > 0) {
            parentHeight = perHeight;
        } else {
            parentHeight = 0;
        }
        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int leftMargin = lp.leftMargin;
            int topMargin = lp.topMargin;

            if (yetWidth + child.getMeasuredWidth() > screenWidth) {
                //另起下一行，已经占用清零
                yetWidth = 0;
                //高在原来基础上在加一块
                parentHeight = parentHeight + perHeight;
            } else {
                yetWidth = yetWidth + perWidth;
            }
            if (yetWidth == 0) {
                yetWidth = perWidth;
            }
            l = yetWidth - perWidth;
            t = parentHeight - perHeight;
            child.layout(l, t, yetWidth, parentHeight);

        }

    }


    /***
     * 数据源
     * @param list
     */
    public void setData(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(list.get(i));
            textView.setBackgroundResource(R.drawable.flow_layout_bg);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(perWidth, perHeight);
            layoutParams.setMargins(margin, margin, margin, margin);
            textView.setLayoutParams(layoutParams);
            this.addView(textView);
        }
    }

    /**
     * 获取一个view
     *
     * @param s
     * @return
     */
    public TextView getView(String s) {
        TextView textView = new TextView(context);
        textView.setText(s);
        textView.setBackgroundResource(R.color.bangumi_index_yellow_bg);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new LayoutParams(perWidth, perHeight));
        return textView;
    }

    /**
     * 设置选择样式
     *
     * @param b
     */
    public void setCheckStyle(boolean b) {
        this.isSingleCheck = b;
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
