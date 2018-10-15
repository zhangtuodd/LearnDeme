package mvvm;

import android.support.annotation.NonNull;

import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class MainMActivity extends BaseMvvmActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.main_m_activity;
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new MainViewModel(getNavigator());
    }
}
