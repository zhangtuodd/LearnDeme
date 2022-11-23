package clazz

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/16
 */
object MultiThreadSleep {

    @JvmStatic
    fun main(args: Array<String>) {
        val t0 = Thread(Task())
        val t1 = Thread(Task().apply { this.flag = false })
        t1.start()
//        try {
//            Thread.sleep(1000)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
        t0.start()
    }
}

class T {}
class K {}

class Task : Runnable {
    var flag = true

    override fun run() {
        if (flag) {
            synchronized(K::class.java) {
                println("${Thread.currentThread().id}---抢到锁")
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                println("${Thread.currentThread().id}---3s睡醒了")

            }
        } else {

            synchronized(K::class.java) {
                println("${Thread.currentThread().id}---抢到锁")
                try {
                    Thread.sleep(4000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                println("${Thread.currentThread().id}---")
            }
        }
    }

}