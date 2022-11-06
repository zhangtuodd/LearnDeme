package databinding;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.util.Log;

import com.example.zhangtuo.learndeme.BR;
import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutGoodsBinding;

import java.util.Random;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/9/29
 */

public class BIndActivity extends BaseActivity {
    private User user;
    Goods goods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        user = new User("zhangsan", "pwd");
        user.setName("lisi");
        BindActivityLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout);
        binding.setUser(user);
        */

        BindActivityLayoutGoodsBinding binding2 = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_goods);
        goods = new Goods("milk", "while", 2.4f);
        goods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int propertyId) {
                if (propertyId == BR.name) {
                    Log.i("BIndActivity", "BR.name");
                } else if (propertyId == BR.details) {
                    Log.i("BIndActivity", "BR.details");
                } else if (propertyId == BR._all) {
                    Log.i("BIndActivity", "BR._all");
                } else {
                    Log.i("BIndActivity", "other");
                }
            }
        });
        binding2.setGoods(goods);
        binding2.setGoodsHandler(new GoodsHandler());
    }

    public class GoodsHandler {
        public void changeGoodsName() {
            goods.setName("code" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

        public void changeGoodsDetails() {
            goods.setDetails("hi" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

    }
}
