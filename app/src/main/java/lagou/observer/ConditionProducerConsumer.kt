package lagou.observer

import okhttp3.internal.notify
import okhttp3.internal.notifyAll

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/13
 */
internal object ConditionProducerConsumer {

    @JvmStatic
    fun main(args: Array<String>) {
        val tOffer = Thread() { kotlin.run { Factory.offer() } }
        val tConsume = Thread() {
            kotlin.run {
                Factory.consume()
            }
        }
        tOffer.start()
        tConsume.start()
    }
}

object Factory {

    val product = arrayListOf<Int>()
    val o = Object()
    val maxSize = 1000
    var model = 0

    fun offer() {
        while (true) { //  todo 不加锁可能导致并发修改异常？观察执行顺序
            synchronized(o) { //if和while区别：notifyAll当方法没有执行完不会释放锁 https://www.jianshu.com/p/ffc0c755fd8d
//                while (product.size < maxSize) {
//                    model++
//                    product.add(model)
//                    println("生产了${model}--容量为${product.size}")
//                    // 提示消费者消费
//                    o.notifyAll()
//                }

                if (product.size < maxSize) {
                    model++
                    product.add(model)
                    println("生产了${model}--容量为${product.size}")
                }
                //提示消费者消费
                o.notifyAll()
            }
        }
    }

    fun consume() {
        while (true) {
            synchronized(o) {
//                while (product.isNotEmpty()) {
//                    val s = product.removeLast()
//                    println("--消费了${s}--容量为${product.size}")
//                    //提示生产者生产
//                    o.notifyAll()
//                }

                if (product.isNotEmpty()) {
                    val s = product.removeLast()
                    println("--消费了${s}--容量为${product.size}")
                }
                //提示生产者生产
                o.notifyAll()
            }
        }


    }
}