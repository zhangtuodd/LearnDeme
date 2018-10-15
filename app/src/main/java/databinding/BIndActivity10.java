package databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivity10Binding;

/**
 * 描述： @BindingAdapter("参数1","参数2")
 * <p>
 * 数据驱动UI，数据跟相关UI绑定，数据变了就会更新Ui(方式：可能走BindingAdapter)
 *
 * @author zhangtuo
 * @date 2018/10/12
 */

public class BIndActivity10 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivity10Binding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_10);
        Activity10Model model = new Activity10Model(this);
        binding.setViewModel(model);
    }
}
