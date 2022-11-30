package design_mode.factory

/**
 * 产品1接口 - 车接口
 */
interface ICar {
    fun name()
}

/**
 * 具体的产品实现
 */
class BenzCar : ICar {
    override fun name() {
        println("奔驰车")
    }
}

class BMWCar : ICar {
    override fun name() {
        println("宝马车")
    }
}


/**
 * 产品2接口 - 手表接口
 */
interface IWatch {
    fun name()
}

class BMWWatch : IWatch {
    override fun name() {
        println("宝马手表")
    }
}

class BenzWatch : IWatch {
    override fun name() {
        println("奔驰手表")
    }
}
