package design_mode.factory

/**
 * 工厂方法（多态工厂）：在简单工厂中，创建对象的是另一个类，而在工厂方法中，是由不同的工厂子类来创建对象
 * 系统在增加新产品时就不会修改工厂类逻辑而是添加新的工厂子类和产品实现，从而弥补简单工厂模式对修改开放的缺陷。
 * 有四个部分组成：产品接口、具体的产品子类实现、工厂接口、工厂实现。根据不同的工厂创建不同的产品类实现。
 * 良好的模式可以新增，拒绝修改
 *
 * @author zhangtuo
 * @date 2022/11/30
 */
object Test2 {
    @JvmStatic
    fun main(args: Array<String>) {
        BMWFactory().create().drive()
        BenzFactory().create().drive()
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