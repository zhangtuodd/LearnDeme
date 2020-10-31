package client.cloudy.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明注解
 */
@Target(ElementType.TYPE)  //作用域 这里是类
@Retention(RetentionPolicy.CLASS) //生命周期 作用范围
public @interface BindPath {
    String path();//路径参数
}
