package ui.immerse;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.zhangtuo.learndeme.R;

/**
 * Created by zhangtuo on 2017/10/13.
 */

public class CompatToolbar extends Toolbar {

    private int immersedColor = Color.WHITE;

    private int titleBlockColor = Color.GRAY;
    private int titleImageResource = R.mipmap.back;
    private int toolbarHeight = -2;
    private String titleName = "titleName";
    private int titleSize = (int) dp2px(20);
    private int titleColor = Color.BLACK;


    private CustomClickListener mListener;

    public CompatToolbar(Context context) {
        this(context, null);
    }

    public CompatToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompatToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        setup(context);
    }

    private void initAttr(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CompatToolbar);
        immersedColor = typedArray.getColor(R.styleable.CompatToolbar_immersed_bg_color, immersedColor);
        titleImageResource = typedArray.getResourceId(R.styleable.CompatToolbar_title_image, titleImageResource);
        titleBlockColor = typedArray.getColor(R.styleable.CompatToolbar_title_block_bg_color, titleBlockColor);
        titleName = typedArray.getString(R.styleable.CompatToolbar_title_name);
        titleColor = typedArray.getColor(R.styleable.CompatToolbar_title_color, titleColor);
        titleSize = (int) typedArray.getDimension(R.styleable.CompatToolbar_title_size, titleSize);

        typedArray.recycle();
    }

    public void setup(Context context) {
        int compatPadingTop = 0;
        // android 4.4以上将Toolbar添加状态栏高度的上边距，沉浸到状态栏下方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            compatPadingTop = getStatusBarHeight();
        }
        this.setPadding(getPaddingLeft(), getPaddingTop() + compatPadingTop, getPaddingRight(), getPaddingBottom());


        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(titleBlockColor);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, toolbarHeight);
        params.gravity = Gravity.CENTER_VERTICAL;
        frameLayout.setLayoutParams(params);

        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setText(titleName);
        tv.setTextSize(titleSize);
        tv.setTextColor(titleColor);
        frameLayout.addView(tv);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(titleImageResource);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(-2, -1);
        imgParams.gravity = Gravity.CENTER;
        imageView.setPadding(30, 30, 30, 30);
        imageView.setLayoutParams(imgParams);
        frameLayout.addView(imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v);
                }
            }
        });


        this.addView(frameLayout);
        this.setBackgroundColor(immersedColor);
    }

    public void setCustomClickListener(CustomClickListener listener) {
        this.mListener = listener;
    }

    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        Log.i("tag", "状态栏高度：" + px2dp(statusBarHeight) + "dp");
        return statusBarHeight;
    }

    public float px2dp(float pxVal) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    public float dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }
}
