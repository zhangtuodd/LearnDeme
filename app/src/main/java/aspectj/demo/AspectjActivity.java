package aspectj.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.R;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectjActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspectj);

        Animal animal = new Animal();
        LogUtils.e("zhangsan", " onCreate fly start...");
        animal.fly();
        LogUtils.e("zhangsan", " onCreate finish...");
    }
}
