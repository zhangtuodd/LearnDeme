package design_mode.factory

/**
 * 工厂方法（多态工厂）：不同于简单工厂-创建对象是一个工厂类，在工厂方法中是由不同的工厂子类来创建对象
 * 系统在增加新产品时就不会修改工厂类逻辑而是添加新的工厂子类和产品实现，从而弥补简单工厂模式对修改开放的缺陷。
 * 有四个部分组成：产品接口、具体的产品子类实现、工厂接口、工厂实现。根据不同的工厂创建不同的产品类实现。
 *
 * 关键类：工厂接口 - 只存在一个创建产品接口的方法
 *
 * 良好的模式可以新增，拒绝修改
 *
 * @author zhangtuo
 * @date 2022/11/30
 */
object Test2 {
    @JvmStatic
    fun main(args: Array<String>) {
        BMWFactory().create().name()
        BenzFactory().create().name()
    }
}

class BMWFactory : IFactory {
    override fun create(): ICar {
        return BMWCar()
    }
}

class BenzFactory : IFactory {
    override fun create(): ICar {
        return BenzCar()
    }
}

interface IFactory {
    fun create(): ICar
}