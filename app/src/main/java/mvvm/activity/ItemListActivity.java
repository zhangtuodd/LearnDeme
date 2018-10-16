package mvvm.activity;

import android.support.annotation.NonNull;

import com.example.zhangtuo.learndeme.R;

import mvvm.vm.ItemListViewModel;
import mvvm.vm.ViewModel;

/**
 * 多条目recyclerView
 * <p>
 * (流程梳理：xml控件通过viewmodel来拿到数据源，控件和model绑定后，通过@BIndingAdapter来操作数据更新UI)
 * <p>
 * recyclerView 和 viewmodel的数据 通过Databinding绑定到一起，recyclerView布局持有列表数据，通过bindingAdapter
 * 来初始化recyclerView控件，建立adapter，然后setAdapter
 * 注 ： adapter中的条目和数据也需要绑定，在onCreateViewHolder里面通过layout创建ViewDataBinding
 * 在onBindViewHolder通过position获取到对应条目的model，然后绑定
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class ItemListActivity extends BaseMvvmActivity {
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
