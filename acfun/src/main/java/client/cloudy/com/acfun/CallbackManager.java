package client.cloudy.com.acfun;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-04
 */
public class CallbackManager {
    private static List<Callback> callbacks = new ArrayList<>();

    public static void add(Callback callback){
        callbacks.add(callback);
    }

    public static void remove(Callback callback){
        callbacks.remove(callback);
    }
}
