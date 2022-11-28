package l_thread


/**
 * 多线程交替打印：线程a打印A，线程b打印B
 *
 * @author zhangtuo
 * @date 2022/11/27
 */
object MultiThreadSwapPrint2 {

    val obj = Object()

    @Volatile
    var flag = true

    fun print() {
        flag = !flag
        println("${Thread.currentThread().name}--打印")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Thread({
            while (true) {
                synchronized(obj) {
                    obj.notify()
                    if (flag) {
                        print()
                        obj.wait()
                    }
//                    else{ // 可有可无，有的话不会再参与竞争，只能等另一个线程唤醒。提高效率
//                        obj.wait()
//                    }
                }
            }
        }, "ThreadA").start()

        Thread({
            while (true) {
                synchronized(obj) {
                    obj.notify()
                    if (!flag) {
                        print()
                        obj.wait()
                    }
//                    else{ // 可有可无，有的话不会再参与竞争，只能等另一个线程唤醒。提高效率
//                        obj.wait()
//                    }
                }
            }
        }, "ThreadBB").start()


    }
}