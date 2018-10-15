package databinding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.util.GlideUtils;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/10
 */

public class Utils {
    @BindingAdapter("recycler_imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        GlideUtils.get(imageView.getContext(), imageView, url);
    }

    @BindingAdapter("recycler_text")
    public static void bindTv(TextView view, String text) {
        if (!TextUtils.isEmpty(text)) {
            view.setText("change--- : " + text);
        }

    }
}
