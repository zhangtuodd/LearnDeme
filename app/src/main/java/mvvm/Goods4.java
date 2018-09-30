package mvvm;

import android.databinding.ObservableField;

/**
 * 双向绑定书记
 *
 * @author zhangtuo
 * @date 2018/9/30
 */

public class Goods4 {

    private ObservableField<String> name = new ObservableField<>();

    public ObservableField<String> getName() {
        return name;
    }
}
