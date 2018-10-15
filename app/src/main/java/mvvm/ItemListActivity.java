package mvvm;

import android.support.annotation.NonNull;

import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class ItemListActivity  extends BaseMvvmActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_item_list;
    }

    @NonNull
    @Override
    protected mvvm.ViewModel createViewModel() {
        return new ItemListViewModel(getNavigator());
    }
}
