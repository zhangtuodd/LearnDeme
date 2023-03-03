package leet.string

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/1/9
 */
object CalString {
    @JvmStatic
    fun main(args: Array<String>) {
        println(calResult("123","456"))
//        println(calResult("0","0"))
    }

    fun calResult(v1: String, v2: String) :String{
        val n1 = v1.length
        val n2 = v2.length
        val array = IntArray(n1 + n2)
        for (i in n1 - 1 downTo 0) {
            for (j in n2 - 1 downTo 0) {
                val a = v1.get(i) - '0'
                val b = v2[j] - '0'
                var r = a*b
                r+=array[i+j+1]
                array[i+j+1] = r%10
                array[i+j] += r/10
            }
        }
        val sb = StringBuilder()
        for (i in 0 until array.size){
            if (sb.isEmpty() && array[i] == 0){
                continue
            }
            sb.append(array[i])
        }
        if (sb.isEmpty()){
            return "0"
        }
        return sb.toString()


    }
}