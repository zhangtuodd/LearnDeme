package l_thread

/**
 * 多线程顺序执行 - join
 *
 * @author zhangtuo
 * @date 2022/11/27
 */
object MultiThreadSortRun {

    @JvmStatic
    fun main(args: Array<String>) {

        val t1 = Thread({
            print.invoke(1)
        }, "ThreadA")

        val t2 = Thread({
            print.invoke(2)
        }, "ThreadB--")
        val t3 = Thread({
            print.invoke(3)
        }, "ThreadC--**")

        t1.start()
        t1.join()
        t2.start()
        t2.join()
        t3.start()
    }


    val print: (Int) -> Unit = {
        for (i in 0..10){
            println("${Thread.currentThread().name}::${i}")
        }

    }

}