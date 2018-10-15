package mvvm;

/**
 * 前提条件
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class PreConditions {
    public static <T> void checkNotNull(T value, String name) {
        if (value == null) {
            throw new NullPointerException(name + "should not be null");
        }
    }
}
