package mvvm;


import androidx.databinding.ViewDataBinding;

import mvvm.vm.ViewModel;

/**
 * databinding 绑定器
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public interface ViewModelBinder {
    void bind(ViewDataBinding binding, ViewModel viewModel);
}
