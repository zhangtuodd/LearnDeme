package deep_copy;

/**
 * 深拷贝相当于克隆，当本体和克隆互不影响
 * 浅拷贝相对于本体和影子，本体和影子都指向同一块内存地址，一方操作后两者都生效
 * 浅拷贝实现：
 * Person p = new Person;
 * Person p1 = p;
 * <p>
 * 深拷贝实现：
 * 1。new新对象
 * 2。该对象的所有子对象实现Cloneable，重写该对象以及所有子对象的clone方法（super.clone）
 *
 * @author zhangtuo
 * @date 2020-10-14
 */
public class main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p = new Person(23, "zhang");
        Person p1 = (Person) p.clone();
        p1.setName("wang");
        String result = p.getName() == p1.getName()
                ? "clone是浅拷贝的" : "clone是深拷贝的";

        System.out.println(result);
        System.out.println(System.identityHashCode(p.getName().hashCode()));
        System.out.println(System.identityHashCode(p1.getName().hashCode()));
        System.out.println(p.toString());
        System.out.println(p1.toString());

    }
}
