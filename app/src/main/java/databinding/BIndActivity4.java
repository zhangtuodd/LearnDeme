package databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutByDoubleBinding;

/**
 * 双向绑定数据
 * <p>
 * 数据改变时刷新视图，视图改变时也改变数据
 *
 * 当 EditText 的输入内容改变时，会同时同步到变量 goods,绑定变量的方式比单向绑定多了一个等号：android:text="@={goods.name}"
 *
 * @author zhangtuo
 * @date 2018/9/30
 */

public class BIndActivity4 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivityLayoutByDoubleBinding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_double);
        Goods4 goods4 = new Goods4();
        goods4.getName().set("init");
        binding.setGoods(goods4);
    }
}
