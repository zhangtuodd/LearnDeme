package databinding;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

/**
 * BindingConversion
 *
 * @author zhangtuo
 * @date 2018/10/8
 */

public class BIndActivity7 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_binding_conversion);
    }
}
