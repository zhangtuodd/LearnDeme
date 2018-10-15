package mvvm;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;

/**
 * databinding 绑定器
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public interface ViewModelBinder {
    void bind(ViewDataBinding binding, ViewModel viewModel);
}
