package l_thread.lagou

import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/16
 * 设置中断标记位，利用中断标记控制线程中断
 * 用来停止线程
 */
object MultiThread_Interrupt {

    @JvmStatic
    fun main(args: Array<String>) {
        val thread = Thread(Task())

        thread.start()
        try {
            Thread.sleep(500)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        thread.interrupt()//设置中断标记位
        thread.isInterrupted
        Thread.interrupted()

    }
}

class Task : Runnable {

    override fun run() {
        val currentThread = Thread.currentThread()
        while (true) {
//            if (currentThread.isInterrupted) {//
//                break
//            }
            if (Thread.interrupted()) {//会清除中断标记
                break
            }
        }
        println("state----------${currentThread.isInterrupted}")
    }
}

