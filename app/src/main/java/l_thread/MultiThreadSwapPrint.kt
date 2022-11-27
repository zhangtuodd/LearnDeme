package l_thread


/**
 * 1-100线程交替打印，线程A - 1，线程B - 2
 *
 * @author zhangtuo
 * @date 2022/11/27
 */
object MultiThreadSwapPrint {

    val obj = Object()

    @Volatile
    var i = 1

    val unit = te@{
        while (true) {
            synchronized(obj) {
                obj.notify()
                if (i <= 100) {
                    println("${Thread.currentThread().name}--:${i}")
                    i++
                    obj.wait()
                } else {
                    return@te
                }
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Thread({
            unit.invoke()
        }, "ThreadA").start()

        Thread() {
            unit.invoke()
        }.start()

    }
}