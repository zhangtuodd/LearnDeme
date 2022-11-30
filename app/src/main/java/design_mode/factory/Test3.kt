package design_mode.factory

/**
 * 抽象工厂：不同于简单工厂、工厂方法中只能创建一类产品，而抽象工厂中可以实现多种产品
 * （在工厂接口提供一系列互相关联的方法去支持多种产品能力，前提时这些产品跟工厂有关系。比如车厂不可能去造鞋子，但有可能造车和手表）
 * 有四个部分组成：产品接口、具体的产品子类实现、工厂接口、工厂实现。根据不同的工厂创建不同的产品类实现。
 *
 * 关键类：工厂接口 - 存在多个创建不同产品接口的方法
 *
 *  如果要新增产品实现，也需要去修改工厂接口，不符合开闭原则，实际情况需要权衡
 * @author zhangtuo
 * @date 2022/11/30
 */
object Test3 {
    @JvmStatic
    fun main(args: Array<String>) {
        BMWFactory1().let {
            it.createCar().name()
            it.createWatch().name()
        }

        BenzFactory1().let {
            it.createCar().name()
            it.createWatch().name()
        }
    }
}

/**
 * 工厂接口
 */
interface IIFactory {
    fun createCar(): ICar
    fun createWatch(): IWatch
}

class BenzFactory1 : IIFactory {
    override fun createCar(): ICar {
        return BenzCar()
    }

    override fun createWatch(): IWatch {
        return BenzWatch()
    }

}

class BMWFactory1 : IIFactory {
    override fun createCar(): ICar {
        return BMWCar()
    }

    override fun createWatch(): IWatch {
        return BMWWatch()
    }

}



