package mvvm.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.zhangtuo.learndeme.BR;
import com.example.zhangtuo.learndeme.BaseActivity;

import mvvm.BindAdapters;
import mvvm.Navigator;
import mvvm.PreConditions;
import mvvm.ViewModelBinder;
import mvvm.vm.ViewModel;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public abstract class BaseMvvmActivity extends BaseActivity {

    ViewDataBinding binding;

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract ViewModel createViewModel();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
//        binding.setVariable(BR.vm, createViewModel());
        getBinder().bind(binding, createViewModel());
    }

    @Override
    protected void onDestroy() {
        getBinder().bind(binding, null);
        binding.executePendingBindings();
        super.onDestroy();
    }

    @NonNull
    protected Navigator getNavigator() {
        return new Navigator() {
            @Override
            public void navigateToAdapter() {
                navigate(ItemListActivity.class);
            }

            private void navigate(Class<?> destination) {//目的地
                Intent intent = new Intent(BaseMvvmActivity.this, destination);
                startActivity(intent);
            }
        };
    }

    @Nullable
    public ViewModelBinder getBinder() {
        ViewModelBinder viewModelBinder = BindAdapters.getDefaultBinder();
        PreConditions.checkNotNull(viewModelBinder, "default binder");
        return viewModelBinder;
    }
}
