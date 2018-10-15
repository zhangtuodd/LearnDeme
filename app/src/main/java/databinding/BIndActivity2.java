package databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutGoods2Binding;

import java.util.Random;

/**
 * 方式2
 * 数据bean中的元素使用ObservableField来包装，不用再继承BaseObservable。。。在getter注解，setter后notify，
 * 改变值的时候只需要获取元素的包装的ObservableField然后set即可
 *
 * @author zhangtuo
 * @date 2018/9/29
 */

public class BIndActivity2 extends BaseActivity {
    Goods2 goods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivityLayoutGoods2Binding binding2 = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_goods2);
        goods = new Goods2("milk", "while", 2.4f);
        binding2.setGoods(goods);
        binding2.setGoodsHandler(new GoodsHandler());
    }

    public class GoodsHandler {
        public void changeGoodsName() {
            goods.getName().set("code" + new Random().nextInt(100));
            goods.getPrice().set(new Random().nextInt(100));
        }

        public void changeGoodsDetails() {
            goods.getDetails().set("hi" + new Random().nextInt(100));
            goods.getPrice().set(new Random().nextInt(100));
        }

    }
}
