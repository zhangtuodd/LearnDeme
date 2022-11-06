package databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/8
 */

public class People extends BaseObservable {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> gender = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();

}