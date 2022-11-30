package design_mode.factory

/**
 * 产品接口
 */
interface ICar {
    fun drive()
}

/**
 * 具体的产品实现
 */
class BenzCar : ICar {
    override fun drive() {
        println("奔驰车")
    }
}

class BMWCar : ICar {
    override fun drive() {
        println("宝马车")
    }

}