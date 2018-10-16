package mvvm.vm;

import android.support.annotation.DrawableRes;
import android.widget.Toast;

import com.example.zhangtuo.learndeme.App;
import com.example.zhangtuo.learndeme.R;

import io.reactivex.functions.Action;
import mvvm.Navigator;

/**
 * recyclerView 每个item对应 的 数据model
 *
 * @author zhangtuo
 * @date 2018/10/16
 */

public class ItemViewModel implements ViewModel {
    public final String name;
    @DrawableRes
    public final int imageRes;

    public final Action onClickNavigator;

    public ItemViewModel(String item, final Navigator navigator) {
        this.name = item;
        String c = String.valueOf(item.charAt(item.length() - 1));
        final int i = Integer.parseInt(c);
        if (i % 2 == 0) {
            this.imageRes = R.mipmap.bg_img_red;
        } else {
            this.imageRes = 0;
        }

        this.onClickNavigator = new Action() {
            @Override
            public void run() throws Exception {
                if (i % 2 == 0) {
                    Toast.makeText(App.mInstance, name, Toast.LENGTH_SHORT).show();
                } else {
                    navigator.navigateToRecyclerItem();
                }
            }
        };
    }


}
