package l_thread.produce_consume

/**
 * 生产消费者：仓库
 */
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
                    println("${Thread.currentThread().name}-仓库容量满了，暂停止生产")
                    o.wait()
                } else {
                    currCount++
                    list.add(currCount)
                    println("${Thread.currentThread().name}-生产了${currCount}---仓库容量:${list.size}")
                }
            }
        }
    }


    fun consume(): Boolean {
        synchronized(o) {
            o.notify()
            if (list.isNotEmpty()) {
                val remove = list.removeLast()
                println("${Thread.currentThread().name}-消费了${remove}---仓库容量:${list.size}")
            } else {
                println("${Thread.currentThread().name}-仓库容量为空--暂停止消费")
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