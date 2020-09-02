package aspectj.demo;

import com.example.base.util.LogUtils;

public class Animal {
    public void fly() {
        LogUtils.e("zhangsan", "animal fly method:" + this.toString() + "#fly");
    }
}

