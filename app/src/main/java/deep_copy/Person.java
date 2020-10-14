package deep_copy;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-10-14
 */
public class Person implements Cloneable{

    private int age ;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {}

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Person)super.clone();
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
