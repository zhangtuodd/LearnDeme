package clazz;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/14
 */

public class InstanceVariableInitializer extends SuperClazz {

    {
        System.out.println("代码块---");
    }
    private int i = 1;
    private int j = i + 1;

    public InstanceVariableInitializer(int var) {
        System.out.println(i);
        System.out.println(j);
        this.i = var;
        System.out.println(i);
        System.out.println(j);
    }

    {               // 实例代码块
        j += 3;

    }

    public static void main(String[] args) {
        new InstanceVariableInitializer(8);
    }
}