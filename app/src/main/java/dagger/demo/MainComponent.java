package dagger.demo;

import com.example.zhangtuo.learndeme.MainActivity;

import dagger.Component;

/**
 * Component 注入。将对象注入到依赖的需求方
 *
 * @author zhangtuo
 * @date 2020-10-13
 */

@Component(modules = CarModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);

}
