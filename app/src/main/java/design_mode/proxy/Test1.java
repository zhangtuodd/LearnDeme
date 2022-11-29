package design_mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/29
 */

class Test1 {
    public static void main(String[] args) {
        try {
            Car car = (Car) new ProxyTest().getInstance(new Bus());
            car.jdkProxy();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class ProxyTest implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before---------");
        Object object = method.invoke(target, args);
        System.out.println("after---------");
        return object;
    }

    public Object getInstance(Object target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }
}

interface Car {
    void jdkProxy();
}

class Bus implements Car {
    @Override
    public void jdkProxy() {
        System.out.println("测试");
    }
}
