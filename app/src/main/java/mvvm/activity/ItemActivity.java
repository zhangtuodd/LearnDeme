package mvvm.activity;

import androidx.annotation.NonNull;

import com.example.zhangtuo.learndeme.R;

import mvvm.vm.ViewModel;

/**
 * 详情页
 *
 * @author zhangtuo
 * @date 2018/10/16
 */

public class ItemActivity extends BaseMvvmActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.item_detail_layout;
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return null;
    }
}
