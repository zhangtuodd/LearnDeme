package l_thread.condition

import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import okhttp3.internal.wait
import java.util.concurrent.Semaphore

/**
 * 实现一个容器，提供两个方法，add，size。通过两个线程，线程1添加10个元素到容器中，线程2实时监控元素的个数，
 * 只有当个数到5个时，线程2给出提示
 * @author zhangtuo
 * @date 2022/11/23
 */
object ThreadLeet1 {

    @Volatile
    var flag = true

    @JvmStatic
    fun main(args: Array<String>) {
        val container = Container()
        val obj = Object()


        Thread() {

            for (i in 1..10) {
                synchronized(obj) {
                    if (flag) {
                        container.add(i)
                        println("线程1--add后容器元素个数：${container.size()}")
                        flag = false
                        obj.notifyAll()
                    }
                    println("线程1-----notifyAll")
                }
                Thread.sleep(200)
            }
        }.start()

        Thread() {
            kotlin.run {
                synchronized(obj) {
                    while (container.size() <= 10) {

                        if (container.size() == 5) {
                            //不打印，挂起线程

                        } else {
                            //打印
                            println("线程2--打印容器元素个数：${container.size()}")
                        }
                        flag = true
                        if (container.size() == 10) {
                            break
                        }
                        obj.wait()
                    }
                }
            }
        }.start()
    }
}

class Container() {

    @Volatile
    var list = ArrayList<Int>()


    fun add(i: Int) {
        list.add(i)
    }

    fun size() = list.size


}