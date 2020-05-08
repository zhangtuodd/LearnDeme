package com.example.zhangtuo.learndeme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MarkLabelView extends RelativeLayout {
    private RelativeLayout relativeLayout;
    public MarkLabelView(Context context) {
        this(context,null);
    }

    public MarkLabelView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MarkLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_view_mark_label, this);
        relativeLayout = view.findViewById(R.id.layout);

        TextView tv1 = new TextView(context);
        tv1.setText("231");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_END,RelativeLayout.TRUE);
        params.setMargins(20,20,30,30);
        tv1.setLayoutParams(params);
        relativeLayout.addView(tv1);


        TextView tv2 = new TextView(context);
        tv2.setText("000");
        relativeLayout.addView(tv2);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        params2.setMargins(50,50,0,0);
        tv2.setLayoutParams(params2);
    }
}
