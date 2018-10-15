package mvvm.activity;

import android.support.annotation.NonNull;

import com.example.zhangtuo.learndeme.R;

import mvvm.vm.ItemListViewModel;
import mvvm.vm.ViewModel;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class ItemListActivity  extends BaseMvvmActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_item_list;
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new ItemListViewModel(getNavigator());
    }
}
