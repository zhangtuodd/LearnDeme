package aspectj.demo;

import android.util.Log;

import com.example.base.util.LogUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class MethodAspect {
    private static final String TAG = "zhangsan";

    @Pointcut("call(* aspectj.demo.Animal.fly(..))")
    public void callMethod() {
        LogUtils.e(TAG, "callMethod->");
    }

    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        LogUtils.e(TAG, "getTarget->" + joinPoint.getTarget().toString());
        LogUtils.e(TAG, "getThis->" + joinPoint.getThis());
    }
}
