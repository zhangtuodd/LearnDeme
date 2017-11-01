package ui.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.zhangtuo.learndeme.R;

import utils.SizeUtils;


/**
 * Created by zhangtuo on 2017/11/1.
 * <p>
 * 商机删除／变更pup,项目共用。
 * 踩坑：fuck 折腾半天
 * 坑1:不代码设置宽高不显示
 * 坑2:终于显示，可布局写的宽度不起作用，需要代码设置具体的值
 * 坑3:终于改好了大小刚好，但是显示位置又来坑我，pop以控件为基准显示位置默认是左下方，
 *     正数向右偏移，负数向左。好，设置默认右下角基准(项目效果)，负数向左偏移，OK了
 * 坑4:坑中之坑。点击按钮pop显示，点击空白pop消失---完美。但是仔细测试发现：点击按钮pop显示，再次点击pop消失又显示，
 *     pop显示的时候居然可以点击其他ui执行跳转。。。我考虑是代码逻辑不对，加一堆显示消失判断然并卵。最终加上this.setFocusable(true)
 *     终于ok了 卧槽。。。。。。。。
 *
 */

public class DeletePupView extends PopupWindow {

    private View view;

    public DeletePupView(Context context, final ClickListener listener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.module_base_business_dialog, null);
        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteListener();
                if (isShowing()) {
                    dismiss();
                }
            }
        });
        view.findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.changeListener();
                if (isShowing()) {
                    dismiss();
                }
            }
        });
        this.setContentView(view);
        this.setFocusable(true);//必须写

        this.setTouchable(true);
        this.setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
        //设置宽与高
        setWidth(SizeUtils.dip2px(context, 115));

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);



    }

    public interface ClickListener {
        void deleteListener();

        void changeListener();
    }

}
