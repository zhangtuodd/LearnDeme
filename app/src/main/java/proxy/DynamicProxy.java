package proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangtuo on 2017/10/12.
 * <p>
 * 动态代理
 * <p>
 * JDK 中为我们提供了 Proxy 类来实现动态代理，其中最重要的方法是 newProxyInstance：
 * <p>
 * 解析动态代理的强大：（主体类行为指定义的接口）
 * -- 静态代理需要和主体类一样实现行为接口，一旦主体类行为发生变化，则代理类也得修改，
 * -- 而动态代理只用传入主体类，一旦主体类行为发生变化，通过新增行为接口，主体类再次实现
 * -- 代码通过getProxy()生成相应的行为接口对象，再调用，强大。
 */

public class DynamicProxy implements InvocationHandler {

    //被代理的对象-主体对象
    private Object mTarget;

    public DynamicProxy(Object mTarget) {
        this.mTarget = mTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("movieShow".equals(methodName) || "tvShow".equals(methodName)) {
            if (args[0] instanceof Integer && (int) args[0] < 100000) {
                Log.i("tag", ((int) args[0]) + "块钱，你找别人演吧");

                //不满足条件，主体不执行
                return null;
            }
        }

        Object result = method.invoke(mTarget, args);
        return result;
    }

    /**
     * 获取代理对象
     *
     * @return
     * @param1 被代理类的加载器，用来创建代理类
     * @param2 被代理类实现的接口，创建代理类会实现这些接口
     * @param3 InvocationHandler，代理类进行拦截的入口
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(mTarget.getClass().getClassLoader(), mTarget.getClass().getInterfaces(), this);
    }


}
