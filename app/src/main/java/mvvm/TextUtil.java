package mvvm;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.TextView;

/**
 * @author zhangtuo
 * @BindingAdapter 的使用
 * @date 2018/9/30
 */

public class TextUtil {

    @BindingAdapter({"textAppend"})
    public static void textChange(TextView view, String original) {
        Log.i("TextUtil", "textAppend url : -- " + original);
        view.setText(original + "----BindingAdapter");
    }
}
