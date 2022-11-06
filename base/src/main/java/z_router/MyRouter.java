package z_router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.base.util.LogUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-10-30
 */
public class MyRouter {

    //路由表：也就是映射关系表
    private Map<String, Class<? extends Activity>> map;

    private Context context;

    private volatile static MyRouter aRouter;

    private MyRouter() {
        map = new HashMap<>();
    }

    public static MyRouter getInstance() {
        if (aRouter == null) {
            synchronized (MyRouter.class) {
                if (aRouter == null) {
                    aRouter = new MyRouter();
                }
            }
        }
        return aRouter;
    }

    //这里不会泄漏，我们存的是类 而不是对象
    public void init(Context context) {
        this.context = context;

        //获取所有生成类的name，根据类名反射创建实例、执行putActivity将映射表注册
        List<String> className = getAllActivityUtils("com.example.zhangtuo.learndeme.z_router");
        LogUtils.d("zhangsan", "className " + className);
        for (String cls : className) {
            try {
                Class<?> aClass = Class.forName(cls);
                //判断该类是否是IRouter的子类
                if (IRouter.class.isAssignableFrom(aClass)) {
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {

            }

        }
    }

    /**
     * 给路由表添加数据
     *
     * @param key
     * @param clazz
     */
    public void addActivity(String key, Class<? extends Activity> clazz) {
        if (key != null && clazz != null && !map.containsKey(key)) {
            map.put(key, clazz);
        }
    }


    /**
     * 跳转
     *
     * @param key
     * @param bundle
     */
    public void jumpActivity(String key, Bundle bundle) {
        Class<? extends Activity> aClass = map.get(key);
        if (aClass != null) {
            Intent intent = new Intent();
            intent.setClass(context, aClass);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }
    }


    /**
     * 根据包名找到所有包下的类名  ActivityUtils1604065588819
     *
     * @param packageName
     * @return
     */
    private List<String> getAllActivityUtils(@NonNull String packageName) {
        List<String> list = new ArrayList<>();
        String path;
        try {
            //通过包管理器 获取应用信息类然后获取到apk的完整路径
            path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            //根据apk的完整路径获取编译后的dex文件目录
            DexFile dexFile = new DexFile(path);
            //获取编译后dex文件中的所有class
            Enumeration enumeration = dexFile.entries();
            while (enumeration.hasMoreElements()) {
                String name = (String) enumeration.nextElement();
                LogUtils.d("zhangsan", "classname---" + name);
                if (name.contains(packageName)) {
                    list.add(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
