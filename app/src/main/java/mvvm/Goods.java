package mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.zhangtuo.learndeme.BR;

/**
 * 数据单向绑定
 * 方式1 ： 继承BaseObservable
 * User类继承自BaseObservable，然后在getter上添加注解(私有变量的情况)、在setter中添加notify方法
 *
 * 总结： 繁琐，而且更改某一变量的时候需要BR.v
 *
 * @author zhangtuo
 * @date 2018/9/29
 */

public class Goods extends BaseObservable {

    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String details;

    private float price;

    public Goods(String name, String details, float price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
        //只更新本字段
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        //更新所有字段
        notifyChange();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
