package leetcode.sort

/**
 * 两两交换，每一轮排出最大值放在最右边
 *
 * @author zhangtuo
 * @date 2023/2/17
 */
object bubble1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = arrayListOf(2, 6, 4, 8, 7, 9, 1)
        for (i in 0 until arr.size) {
            for (j in 0 until arr.size - 1) {
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = temp
                }
            }
        }
        println("arr--${arr.toString()}")
    }
}