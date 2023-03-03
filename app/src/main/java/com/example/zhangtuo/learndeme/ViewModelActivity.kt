package com.example.zhangtuo.learndeme

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.base.util.LogUtils
import kotlinx.coroutines.*

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/1/6
 */
class ViewModelActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_view_model_layout)
        val viewModel = ViewModelProvider(this)[VViewModel::class.java]

        viewModel.viewModelScope.launch {
            var value = 1;
            LogUtils.e("ViewModelActivity","${Thread.currentThread().name}-------------1")

            withContext(Dispatchers.IO){
                for (i in 1..100000000){
                    value++
                }
                Thread.sleep(2000)
                LogUtils.e("ViewModelActivity","${Thread.currentThread().name}-------------2")
            }
            LogUtils.e("ViewModelActivity","${Thread.currentThread().name}-------------3--value:${value}")
            findViewById<TextView>(R.id._tv).text = value.toString()

            withContext(Dispatchers.IO){
                for (i in 1..100000){
                    value++
                }
                LogUtils.e("ViewModelActivity","${Thread.currentThread().name}-------------4")
            }
        }

        LogUtils.e("ViewModelActivity","${Thread.currentThread().name}-------------5--")

//        val person = Person()
//        val also: Person = person.also {
//            it.age = 100
//            it.name = "zzz"
//        }
//        LogUtils.e("Person","1----${person}")
//        LogUtils.e("Person","2----${also}")

//        runBlocking {
//           val job = GlobalScope.launch {
//               println("I'm runBlocking start thread:${Thread.currentThread()}")
//               Thread.sleep(2000)
//               println("I'm runBlocking end")
//           }
//            job.join()
//            println("end-------")
//        }
//        main()

        val array = intArrayOf(1,3,5,7,9)
        binarySearch(array,array.size,6)
    }


    fun binarySearch(array: IntArray, size: Int, value: Int): Int {
        var lo = 0
        var hi = size - 1
        while (lo <= hi) {
            val mid = lo + hi ushr 1
            val midVal = array[mid]
            if (midVal < value) {
                lo = mid + 1
            } else if (midVal > value) {
                hi = mid - 1
            } else {
                return mid // value found
            }
        }
        return lo // value not present
    }
    fun testBlock() {
        println("before runBlocking thread:${Thread.currentThread()}")
        //①
        runBlocking {
            println("I'm runBlocking start thread:${Thread.currentThread()}")
            Thread.sleep(2000)
            println("I'm runBlocking end")
        }
        //②
        println("after runBlocking:${Thread.currentThread()}")
    }

    fun testBlock1() {
        println("before runBlocking thread:${Thread.currentThread()}")
        //①
        GlobalScope.launch {
            println("I'm runBlocking start thread:${Thread.currentThread()}")
            Thread.sleep(2000)
            println("I'm runBlocking end")
        }
        //②
        println("after runBlocking:${Thread.currentThread()}")
    }

    fun testBlock2() {
        println("before runBlocking thread:${Thread.currentThread()}")
        //①
       runBlocking {
           val job = GlobalScope.launch {
               println("I'm runBlocking start thread:${Thread.currentThread()}")
               Thread.sleep(2000)
               println("I'm runBlocking end")
           }
           job.join()

           println("after runBlocking:${Thread.currentThread()}")
       }
        //②

    }


    fun testBlock3() {
        println("before runBlocking thread:${Thread.currentThread()}")
        //①
        runBlocking(Dispatchers.IO) {
            println("I'm runBlocking start thread:${Thread.currentThread()}")
            Thread.sleep(2000)
            println("I'm runBlocking end")
        }
        //②
        println("after runBlocking:${Thread.currentThread()}")
    }

    fun main() {
        CoroutineScope(Dispatchers.Default).launch {
            // 并发执行，fun1() 和 fun2() 并行执行（launch 方式）
            this.launch {
                fun1()
                fun2()
            }
//            this.launch {
//                fun2()
//            }
        }
    }
    suspend fun fun1() {
        delay(1000)
        println("fun1")
    }
    suspend fun fun2() {
        delay(1000)
        println("fun2")
    }




    class Person():java.io.Serializable{
        var name:String? = null
        var age:Int? = null
        override fun toString(): String {
            return "Person(name=$name, age=$age)"
        }

    }

}