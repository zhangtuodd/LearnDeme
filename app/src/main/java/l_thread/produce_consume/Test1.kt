package l_thread.produce_consume

/**
 * 生产消费者，最多生产100个，仓库最大容量5
 *
 * 先创建 生产 和 消费者线程
 * 创建仓库类：提供 生产和消费方法，生产满了暂停，消费空了暂停
 *
 * @author zhangtuo
 * @date 2022/11/28
 */
object Test1 {


    @JvmStatic
    fun main(args: Array<String>) {
        val container = Container(5)

        val produceThread = Thread({
            while (container.notArriveMaxProduceCount()) {
                container.produce()
            }
        }, "produce_Thread")

        val consumeThread = Thread({
            while (true) {
                if (container.consume()) { //消费者没有条件，一直消费
                    return@Thread
                }
            }
        }, "consume_Thread")

        produceThread.start()
        consumeThread.start()
    }
}

class Container(private val cap: Int, private val maxProduceCount: Int = 100) {
    @Volatile
    var list = arrayListOf<Int>()

    val o = Object()

    @Volatile
    var currCount = 0

    fun produce() {
        synchronized(o) {
            if (notArriveMaxProduceCount()) {
                o.notify()
                if (list.size >= cap) {
                    println("仓库容量满了，暂停止生产")
                    o.wait()
                } else {
                    currCount++
                    list.add(currCount)
                    println("生产了${currCount}---仓库容量:${list.size}")
                }
            }
        }
    }


    fun consume(): Boolean {
        synchronized(o) {
            o.notify()
            if (list.isNotEmpty()) {
                val remove = list.removeLast()
                println("消费了${remove}---仓库容量:${list.size}")
            } else {
                println("仓库容量为空--暂停止消费")
                if (notArriveMaxProduceCount().not()) {
                    return true
                }
                o.wait()
            }
            return false
        }
    }

    fun notArriveMaxProduceCount() = currCount < maxProduceCount
}