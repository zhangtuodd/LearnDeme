package dagger.demo;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-10-13
 */
public class Car {

    private Light light;


    public Car() {
        light = new Light();
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "Car{" +
                "light=" + light +
                '}';
    }
}
