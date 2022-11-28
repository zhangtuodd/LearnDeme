package l_thread


/**
 * 多线程交替打印：abcdefg
 *
 * @author zhangtuo
 * @date 2022/11/27
 */
object MultiThreadSwapPrint3 {

    private val obj = Object()
    const val content = "abcdefghijklmnopqrst"

    @Volatile
    var index = 0


    fun print(temp: Char) {
        println("${Thread.currentThread().name}--打印${temp}")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Thread({
            while (index < content.length) {
                synchronized(obj) {
                    obj.notify()
                    print(content[index])
                    index++
                    if (index < content.length) {//程序结束时避免wait阻塞
                        obj.wait()
                    } else {
                        return@Thread
                    }

                }
            }
        }, "ThreadA").start()

        Thread({
            while (index < content.length) {
                synchronized(obj) {
                    obj.notify()
                    print(content[index])
                    index++
                    if (index < content.length) { //程序结束时避免wait阻塞
                        obj.wait()
                    } else {
                        return@Thread
                    }
                }
            }
        }, "ThreadBB").start()


    }
}