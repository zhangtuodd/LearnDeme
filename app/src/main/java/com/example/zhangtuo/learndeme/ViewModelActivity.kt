package com.example.zhangtuo.learndeme

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.base.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    }

}