package com.example.zhangtuo.learndeme

import android.os.Bundle

/**
 * 模拟内存泄漏
 *
 * @author zhangtuo
 * @date 2023/2/26
 */
class TestActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TestManager.add(this)
        TestManager.add(this)
    }

}