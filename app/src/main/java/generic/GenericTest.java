package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/12/3
 */

class GenericTest<T> {
     T tt = null;
    public static void main(String[] args) {

    }

    ArrayList<String> a  = new ArrayList<>();
    void add(){
//        a.add(1);
//        a.add("");
    }

    //泛型方法,不依赖与泛型类，独立使用，方法内的T和类中的T可以不是一个类型
    public  <T> void setValue(T t){
//        this.tt = t;
        if (t instanceof String){
            
        }
    }
}
