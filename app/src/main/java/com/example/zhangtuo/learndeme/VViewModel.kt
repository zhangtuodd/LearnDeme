package com.example.zhangtuo.learndeme

import androidx.lifecycle.ViewModel

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/1/6
 */
fun Int.toA():Int{
    return this+100
}
class VViewModel : ViewModel() {
    fun load() {
    }

    fun main() {
        foo {
            println("I love meitu")
        }
    }
    fun foo(block: () -> Unit) {
        println("before block")
        block()
        println("after block")
    }
}