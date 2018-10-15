package databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutByBindingAdapterBinding;

/**
 * @author zhangtuo
 * @date 2018/9/30
 * @BindingAdapter ：中转机制
 * 也就是当databinding计算出值后，我们可以利用这个机制，做相应的操作(类型转变，判空，再次运算等等)
 * 用法：
 * app:textAppend="@{user.url}"
 * user.url这个值被textAppend注解过的方法监听，如下
 * @BindingAdapter({"textAppend"})
 * public static void textChange(TextView view, String original) {
 * //do sth..
 * }
 * 注意 ： @BindingAdapter({"android:text"})  注解可以和控件原有属性一样，但会覆盖原有属性（为了代码走查方便，命名应避免覆盖原有属性	）
 * 当在任意一个View的任意一个属性上使用binding表达式时，DataBinding框架的处理过程分成三步：
 * 1、对binding表达式求值
 * 2、寻找合适的BindingAdapter，如果找到，就调用它的方法
 * 3、如果没有找到合适的BindingAdapter，就在View上寻找合适的方法调用
 */

public class BIndActivity6 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivityLayoutByBindingAdapterBinding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_binding_adapter);
        Goods2 goods2 = new Goods2("zhang", "detail", 2.0f);
        binding.setGoods(goods2);
    }
}
