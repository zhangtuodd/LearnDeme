package l_thread

import java.util.concurrent.*

/**
 * 观测shutdown 和shutdownNow 执行流程
 * 线程池的几种状态
 * https://blog.csdn.net/GoGleTech/article/details/79728522
 *
 * @author zhangtuo
 * @date 2022/11/29
 */
object ThreadPoolState {

    private final val list = ArrayList<String>()

    private final val a = 1

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        list.add("11")
        val poolExecutor = ThreadPoolExecutor(
            50, 100, 0L, TimeUnit.MILLISECONDS,
            LinkedBlockingQueue<Runnable>(100000)
        )
        for (i in 0..2000) {
            poolExecutor.execute {
                print(1)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
//        val tpe = es as ThreadPoolExecutor
        var i = 1
        while (i < 10) {
            i++
            println()
            val queueSize = poolExecutor.queue.size
            println("当前阻塞队列中的任务数：$queueSize")
            val activeCount = poolExecutor.activeCount
            println("当前正在执行任务线程数：$activeCount")
            val completedTaskCount = poolExecutor.completedTaskCount
            println("已经执行完成任务数：$completedTaskCount")
            val taskCount = poolExecutor.taskCount
            println("总任务数：$taskCount")
            Thread.sleep(2000)
        }
        poolExecutor.shutdown()
        val taskList: MutableList<Runnable> = poolExecutor.shutdownNow()

        println("shutdownNow后阻塞队列中的任务数----${taskList.size}")

        while (true) {
            println("===================================")
            println("--shutdownNow()-----------------------------------")
            val queueSize = poolExecutor.queue.size
            println("--当前阻塞队列中的任务数：$queueSize")
            val activeCount = poolExecutor.activeCount
            println("--当前正在执行任务线程数：$activeCount")
            val completedTaskCount = poolExecutor.completedTaskCount
            println("--已经执行完成任务数：$completedTaskCount")
            val taskCount = poolExecutor.taskCount
            println("--总任务数：$taskCount")
            Thread.sleep(2000)
            if (activeCount == 0) {
                return
            }
        }

    }
}

class Task : Runnable {
    override fun run() {
        Thread.sleep(100)
    }


}