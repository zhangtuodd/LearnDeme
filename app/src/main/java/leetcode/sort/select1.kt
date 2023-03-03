package leetcode.sort

/**
 * 每轮选择一个最小的值放在最前，最差、最佳、平均、时间复杂度都为o(n)
 *
 * @author zhangtuo
 * @date 2023/2/17
 */
object select1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = arrayListOf(6, 2, 4, 5, 9, 7, 1, 3)
        for (i in 0 until arr.size) {
            var minIndex = i
            for (j in i+1 until arr.size) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j
                }
            }
            val a = arr[i]
            arr[i] = arr[minIndex]
            arr[minIndex] = a
        }
        println("arr--:${arr.toString()}")
    }
}