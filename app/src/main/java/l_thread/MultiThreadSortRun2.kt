package l_thread

import java.util.concurrent.Executors

/**
 * 多线程顺序执行  - Executors.newSingleThreadExecutor
 * 单线程化线程池(newSingleThreadExecutor):优点，串行执行所有任务
 *
 * @author zhangtuo
 * @date 2022/11/27
 */
object MultiThreadSortRun2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val threadPool = Executors.newSingleThreadExecutor()

        val t1 = Thread {
            print.invoke("ThreadA")
        }
        val t2 = Thread {
            print.invoke("ThreadB--")
        }
        val t3 = Thread {
            print.invoke("ThreadC--**")
        }

        threadPool.submit(t1)
        threadPool.submit(t2)
        threadPool.submit(t3)
        threadPool.shutdown();//方法用来关闭线程池，拒绝新任务。

    }


    val print: (String) -> Unit = {
        for (i in 0..10) {
            println("${it}::${i}")
        }
    }
}