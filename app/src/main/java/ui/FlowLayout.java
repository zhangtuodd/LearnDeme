package ui;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.zhangtuo.learndeme.R;

import java.util.ArrayList;
import java.util.List;

import activity.FlowLayoutActivity;

/**
 * 一行四分 的 流失布局
 *
 * @author zhangtuo
 * @date 2018/5/10
 */

public class FlowLayout extends ViewGroup {

    List<BlockModel> list = new ArrayList<>();
    Context context;
    int screenWidth;
    int perHeight;//每个块的高度
    int perWidth;
    boolean isSingleCheck = true;//模块单选/多选

    int yetWidth;//已经用的宽度
    int parentHeight;//父view的高度

    int margin;//默认相比块之间的margin
    int offset;//开始定位的偏移量
    List<String> data = new ArrayList<>();//选中数据集合返回

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        data.clear();//选中数据集合首次进入清空
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //真正一屏显示的宽度（除去两边的padding）
        screenWidth = wm.getDefaultDisplay().getWidth();
        perWidth = (screenWidth - dp2px(context, 60)) / 4;
        perHeight = dp2px(context, 30);
        margin = dp2px(context, 10);
        offset = dp2px(context, 5);
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
            parentHeight = perHeight + margin;
        }
        yetWidth = offset;
        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (yetWidth + margin + child.getMeasuredWidth() > screenWidth) {
                //另起下一行，已经占用清零
                yetWidth = 0;
                //高在原来基础上在加一块
                parentHeight = parentHeight + perHeight + margin;
            } else {
                yetWidth = yetWidth + perWidth + margin;
            }
        }
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : screenWidth,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : parentHeight + margin);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        yetWidth = offset;
        if (childCount > 0) {
            parentHeight = perHeight + margin;
        } else {
            parentHeight = 0;
        }
        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) getChildAt(i);
            if (yetWidth + child.getMeasuredWidth() > screenWidth) {
                //另起下一行，已经占用清零
                yetWidth = offset;
                //高在原来基础上在加一块
                parentHeight = parentHeight + perHeight + margin;
            } else {
                yetWidth = yetWidth + perWidth + margin;
            }
            if (yetWidth == offset) {
                yetWidth = perWidth + margin + offset;
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
    public void setData(final List<BlockModel> list) {
        this.list.clear();
        this.list.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            final TextView textView = new TextView(context);
            final BlockModel model = list.get(i);
            textView.setText(model.text);
            textView.setBackgroundResource(R.drawable.flow_layout_default_bg);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#9b9ea9"));
            textView.setTextSize(14);
            textView.setLayoutParams(new LayoutParams(perWidth, perHeight));
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSingleCheck) {
                        for (int k = 0; k < getChildCount(); k++) {
                            getChildAt(k).setBackgroundResource(R.drawable.flow_layout_default_bg);
                            ((TextView) getChildAt(k)).setTextColor(Color.parseColor("#9b9ea9"));
                            model.isCheck = false;
                        }
                        data.clear();
                    }
                    if (!model.isCheck) {
                        textView.setBackgroundResource(R.drawable.flow_layout_check_bg);
                        textView.setTextColor(Color.parseColor("#527efe"));
                        model.isCheck = true;
                        //添加
                        data.add(model.text);
                    } else {
                        textView.setBackgroundResource(R.drawable.flow_layout_default_bg);
                        textView.setTextColor(Color.parseColor("#9b9ea9"));
                        model.isCheck = false;
                        //删除
                        for (int i = 0; i < data.size(); i++) {
                            if (TextUtils.equals(model.text, data.get(i))) {
                                data.remove(i);
                            }
                        }
                    }
                    getData();
                    Log.i("tag", "data --- >>>" + data.size());
                    Log.i("tag", model.text);
                }
            });
            this.addView(textView);
        }
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

    /**
     * 回传数据
     *
     * @return
     */
    public List<String> getData() {
        Log.i("tag", "getData --- >>>" + data.size());
        return data;
    }

}
