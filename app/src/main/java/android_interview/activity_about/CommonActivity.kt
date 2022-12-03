package android_interview.activity_about

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.example.base.util.LogUtils
import com.example.zhangtuo.learndeme.BaseActivity

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/12/2
 */
open class CommonActivity : BaseActivity() {

    fun log(ss: String) {
        val TAG = javaClass.simpleName
        LogUtils.e(TAG, "${ss}-----")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }


    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart")
    }

    override fun finish() {
        super.finish()
        log("finish")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        log("onRestoreInstanceState")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        log("onNewIntent")
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        log("onConfigurationChanged")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        log("onWindowFocusChanged:${hasFocus}")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        log("onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        log("onDetachedFromWindow")
    }

}