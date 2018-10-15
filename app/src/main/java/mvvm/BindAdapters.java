package mvvm;

import android.databinding.BindingConversion;
import android.view.View;

import io.reactivex.functions.Action;

/**
 * 转换器
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class BindAdapters {

    /**
     * 点击跳转的中转
     * --BindingConversion ： 方法可以直接在xml跟踪，比BindingAdapter方便，但是系全局，慎用
     *
     * @param listener
     * @return
     */
    @BindingConversion
    public static View.OnClickListener toOnClickListener(final Action listener) {
        if (listener != null) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        listener.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        } else {
            return null;
        }
    }
}
