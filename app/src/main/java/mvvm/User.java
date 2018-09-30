package mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.zhangtuo.learndeme.BR;

/**
 * 用于data标签，关联xml，做数据关联
 *
 * @author zhangtuo
 * @date 2018/9/29
 */

public class User extends BaseObservable {
    @Bindable
    public String name;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
