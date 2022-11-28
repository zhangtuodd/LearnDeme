package l_thread


/**
 * 多线程交替打印：abcdefg
 *
 * @author zhangtuo
 * @date 2022/11/27
 */
object MultiThreadSwapPrint3 {

    val obj = Object()
    val content = "abcdefg"

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
                    obj.wait()
                }
            }
        }, "ThreadA").start()

        Thread({
            while (index < content.length) {
                synchronized(obj) {
                    obj.notify()
                    print(content[index])
                    index++
                    obj.wait()
                }
            }
        }, "ThreadBB").start()


    }
}