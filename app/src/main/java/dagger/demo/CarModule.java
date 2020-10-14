package dagger.demo;

import dagger.Module;
import dagger.Provides;

/**
 * 用于提供对象
 *
 * @author zhangtuo
 * @date 2020-10-13
 */

@Module //依赖提供方，负责提供依赖中所需要的对象
public class CarModule {

    @Provides  //会根据返回值类型在有此注解的方法中寻找应调用的方法
    public Car getCar() {
        return new Car();
    }
}
