package leetcode


/**
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。 *
 * @author zhangtuo
 * @date 2023/2/15
 */
object FindVal {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = arrayOf(10, 20, 30, 40, 50, 60, 70, 75)
//        find(arr, 73)
    }

    private fun find(arr: Array<Int>, key: Int) {
        var low = 0
        var high = arr.size - 1
        var temp = 0;
        if (key <= arr[low]) {
            temp = arr[low]
            println("目标值：---：${temp}")
            return
        }
        if (key >= arr[high]) {
            temp = arr[high]
            println("目标值：---：${temp}")
            return
        }
        while (low <= high) {
            var mid = (low + high) / 2
            if (arr[mid] == key) {
                temp = key
                break
            } else if (arr[mid] < key) {

                low = mid + 1
                if (arr[low] > key) {
                    val cha = (arr[low] - key) - (key - arr[mid])
                    temp = if (cha > 0) { //低位远点
                        arr[mid]
                    } else if (cha < 0) {
                        arr[low]
                    } else {
                        arr[low]
                    }
                    break
                }
            } else if (arr[mid] > key) {

                high = mid - 1
                if (arr[high] < key) {
                    val cha = (arr[mid] - key) - (key - arr[high])
                    temp = if (cha > 0) {
                        arr[high]
                    } else if (cha < 0) {
                        arr[mid]
                    } else {
                        arr[high]
                    }
                    break
                }
            }
        }
        println("目标值：---：${temp}")
    }


}