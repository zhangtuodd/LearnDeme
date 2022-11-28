package l_thread.produce_consume

/**
 * 生产消费者，最多生产100个，仓库最大容量5
 *
 * 变种多个消费者
 *
 * 先创建 生产 和 消费者线程
 * 创建仓库类：提供 生产和消费方法，生产满了暂停，消费空了暂停
 *
 * @author zhangtuo
 * @date 2022/11/28
 */
object Test2 {


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
        val consumeThread2 = Thread({
            while (true) {
                if (container.consume()) { //消费者没有条件，一直消费
                    return@Thread
                }
            }
        }, "consume_Thread2")

        produceThread.start()
        consumeThread.start()
        consumeThread2.start()
    }
}
