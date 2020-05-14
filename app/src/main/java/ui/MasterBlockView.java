package ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.util.LogUtils;
import com.example.base.util.SizeUtils;
import com.example.zhangtuo.learndeme.R;


public class MasterBlockView extends FrameLayout {
    private static final String TAG = "MasterBlockView";
    private ImageView mMaster_avatar;
    private ImageView mMaster_row;
    private TextView mMaster_content;
    private Context context;
    private boolean status;
    private TextPaint textPaint;
    private String tempWord = "";

    public MasterBlockView(@NonNull Context context) {
        this(context, null);
    }

    public MasterBlockView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MasterBlockView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }


    private void initView() {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.master_layout, null);
        this.addView(layout);

        mMaster_avatar = layout.findViewById(R.id.master_avatar);
        mMaster_row = layout.findViewById(R.id.master_row);
        mMaster_content = layout.findViewById(R.id.master_content);

        textPaint = new TextPaint();

    }

    public void setData(HostInfo hostInfo, VisibleListener listener) {
        if (hostInfo == null || TextUtils.isEmpty(hostInfo.content)) {
            listener.visibleType(false);
            return;
        }

        if (hostInfo.content.equals(tempWord)) {
            return;
        }

        mMaster_content.setSingleLine(true);
        mMaster_row.setImageResource(R.drawable.live_icon_down);
        status = false;


//        ImageLoader.create()
//                .placeHolder(R.drawable.bg_circle_place_holder_small, ImageView.ScaleType.FIT_XY)
//                .failHolder(R.drawable.bg_circle_place_holder_small, ImageView.ScaleType.FIT_XY)
//                .source(mMaster_avatar, hostInfo.avatarImg)
//                .circle()
//                .forceStaticImage(true)
//                .build();

        //默认一行，默认隐藏箭头

        int screenWidth = SizeUtils.getScreenWidth(context);
        //计算剩余位置一行文本的宽度
        int textHolderWidth = screenWidth - 2 * SizeUtils.dip2px(context, 16) - SizeUtils.dip2px(context, (24 + 35));

        LogUtils.i(TAG, "textHolderWidth--------------" + textHolderWidth);
        textPaint.setTextSize(SizeUtils.dip2px(context, 13));
        float desiredWidth = Layout.getDesiredWidth(hostInfo.content, textPaint);
        LogUtils.i(TAG, "desiredWidth--------------" + desiredWidth);
        if (textHolderWidth > desiredWidth) {
            mMaster_row.setVisibility(View.GONE);
        } else {
            mMaster_row.setVisibility(View.VISIBLE);
            mMaster_row.setImageResource(R.drawable.live_icon_down);


            mMaster_row.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (status) {
//                        mMaster_content.setMaxHeight(SizeUtils.dip2px(context, 30));
                        mMaster_content.setSingleLine(true);
                        mMaster_row.setImageResource(R.drawable.live_icon_down);
                    } else {
//                        mMaster_content.setMaxHeight(SizeUtils.dip2px(context, 300));
                        mMaster_content.setSingleLine(false);
                        mMaster_row.setImageResource(R.drawable.live_icon_up);
                    }
                    status = !status;
                }
            });
        }
        mMaster_content.setText(hostInfo.content);

        tempWord = hostInfo.content;
    }

    public interface VisibleListener {
        void visibleType(boolean b);
    }
}
