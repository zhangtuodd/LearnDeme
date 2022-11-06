package databinding;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;

/**
 * 数据单向绑定：方式2
 * 数据bean中的元素使用ObservableField来包装，不用再继承BaseObservable。。。在getter注解，setter后notify，
 * 改变值的时候只需要获取元素的包装的ObservableField然后set即可
 *
 * @author zhangtuo
 * @date 2018/9/29
 */

public class Goods2 {
    private ObservableField<String> name;

    private ObservableFloat price;

    private ObservableField<String> details;

    public Goods2(String name, String details, float price) {
        this.name = new ObservableField<>(name);
        this.price = new ObservableFloat(price);
        this.details = new ObservableField<>(details);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableField<String> getDetails() {
        return details;
    }

    public ObservableFloat getPrice() {
        return price;
    }
}
