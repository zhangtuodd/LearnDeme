package databinding;

import android.content.Context;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangtuo.learndeme.R;

import java.util.ArrayList;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/12
 */

public class Activity10Model {
    public Context context;
    public final ObservableArrayList<String> names = new ObservableArrayList<>();

    public Activity10Model(Context context) {
        names.add("linus chen");
        names.add("lin xueyan");
        names.add("zhang xiaona");
        names.add("chen lei");
        names.add("liu yuhong");

        this.context = context;
    }

    @BindingAdapter({"names", "context"})
    public static void setNames(ViewGroup linearLayout, ArrayList<String> names, Context context) {

        linearLayout.removeAllViews();

        for (String s : names) {
            TextView t = new TextView(context);
            t.setText(s);
            linearLayout.addView(t);
        }
    }

    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.add_btn) {
            names.add("yanyu cai");
        }
    }
}
