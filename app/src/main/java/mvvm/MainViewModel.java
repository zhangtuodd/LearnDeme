package mvvm;


import android.support.annotation.NonNull;

import io.reactivex.functions.Action;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class MainViewModel implements ViewModel{

    @NonNull
    private final Navigator navigator;

    public MainViewModel(@NonNull Navigator navigator) {
        this.navigator = navigator;
    }

    public final Action onAdapterClick = new Action() {
        @Override
        public void run() throws Exception {
            navigator.navigateToAdapter();
        }
    };
}
