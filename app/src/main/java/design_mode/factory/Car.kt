package design_mode.factory

/**
 * 产品接口
 */
interface Car {
    fun produce()
}

/**
 * 具体的产品实现
 */
class BenzCar : Car {
    override fun produce() {
        println("奔驰车")
    }
}

class BMWCar : Car {
    override fun produce() {
        println("宝马车")
    }

}