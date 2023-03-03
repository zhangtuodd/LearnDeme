package leetcode.sort

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/17
 */
object quick1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = arrayListOf(3,2,1,4,5,9,7,8)

    }

    fun sort(arr: ArrayList<Int>,startIndex:Int,endIndex:Int){
        val pivot = arr[startIndex]
        var mark = startIndex
        for (i in startIndex+1 until endIndex) {
            if(pivot > arr[i]){
                mark ++
                val v = arr[mark]
                arr[mark] = arr[i]
                arr[i] = v
            }
        }
    }
}