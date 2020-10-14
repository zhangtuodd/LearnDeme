package synchronize;

import deep_copy.Person;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-10-14
 */
public class O {
    Person person = new Person();

    public void doSth() throws InterruptedException {

//        synchronized (Person.class) {
        synchronized (person) {
//            synchronized (O.class) {
//        synchronized (this) {
            System.out.println("前");

            Thread.sleep(2000);

            System.out.println("后");
        }
    }
}
