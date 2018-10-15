package databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.Toast;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutByEventBinding;

/**
 * 事件绑定
 * 事件绑定也是一种变量绑定，只不过设置的变量是回调接口而已 事件绑定可用于以下多种回调事件
 * android:onClick
 * android:onLongClick
 * android:afterTextChanged
 * android:onTextChanged
 * ...
 *
 * @author zhangtuo
 * @date 2018/9/30
 */

public class BIndActivity5 extends BaseActivity {
    BindActivityLayoutByEventBinding binding;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_event);
        user = new User("zhang", "password");
        binding.setUser(user);
        UserPresenter presenter = new UserPresenter();
        binding.setPresenter(presenter);

    }

    public class UserPresenter {
        public void onUserNameClick() {
            Toast.makeText(BIndActivity5.this, "用户名：" + user.name, Toast.LENGTH_SHORT).show();
        }

        /**
         * 注意以下两种方法的绑定方式
         *
         * @param s
         */
        public void afterTextChanged(Editable s) {
            user.setName(s.toString());
        }

        public void afterUserPasswordChanged(Editable s) {
            user.setPassword(s.toString());
            binding.setUser(user);
        }

    }

}
