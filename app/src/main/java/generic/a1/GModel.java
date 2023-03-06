package generic.a1;

import android.view.View;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/3/3
 */

class GModel<T extends Number> {
    List<T> list = new ArrayList<T>();

    public void add(T t) {
        list.add(t);
    }

    public void print() {
        System.out.println(list.toString());
    }


    /**
     * 运行时通过反射获取泛型的类型
     * @return
     */
    public Class<T> getTClass()
    {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    public void get() {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }
}