package design_mode.factory

import design_mode.factory.Factory.BMW_TYPE
import design_mode.factory.Factory.BENZ_TYPE

/**
 * 简单工厂实现：一个工厂根据不同要求（参数）生产所有类型产品
 * 把实例化对象放到一个单独的类中实现，通过工厂提供对象让调用者和实现者之间解耦
 * 有三个部分组成：产品接口、具体的产品子类实现、工厂。工厂根据调用者的要求（参数）去提供不同的产品类实现。
 * 关键类：工厂类 - 一个工厂根据不同参数生产不同类型产品
 * @author zhangtuo
 * @date 2022/11/30
 */
object Test1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val benzCar = Factory.createCar(BENZ_TYPE)
        benzCar?.name()
        val bmwCar = Factory.createCar(BMW_TYPE)
        bmwCar?.name()
    }
}



object Factory {
    const val BMW_TYPE = 1
    const val BENZ_TYPE = 2
    fun createCar(type: Int): ICar? {
        return if (type == BMW_TYPE) {
            BMWCar()
        } else if (type == BENZ_TYPE) {
            BenzCar()
        } else {
            null
        }
    }
}