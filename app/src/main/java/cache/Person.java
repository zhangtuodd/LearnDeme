package cache;

/**
 * @author： zhangtuo
 * @date： 2019-11-07
 * @description：
 */
public class Person {
    public String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
