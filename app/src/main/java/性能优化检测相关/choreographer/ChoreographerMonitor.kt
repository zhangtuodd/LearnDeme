package 性能优化检测相关.choreographer

import android.content.Context
import android.os.Build
import android.view.Choreographer
import android.view.WindowManager
import com.example.zhangtuo.learndeme.App

/**
 * 掉帧率统计
 * https://zhuanlan.zhihu.com/p/362334212
 *
 * 每次需要开始渲染的时候都会回调doFrame()，如果某2次doFrame()之间的时间差大于16.6ms，则说明发生了UI有点卡顿，已经在掉帧了，拿着这个时间差除以16.6就得出掉了多少帧。
 */
object ChoreographerMonitor {
    @Volatile
    private var isStart = false
    private val monitorFrameCallback by lazy { MonitorFrameCallback() }
    private var mListener: (Int) -> Unit = {}
    private var mLastTime = 0L

//    fun test(){
//        startMonitor {
//            textView.text = "掉帧数为"
//        }
//    }

    fun startMonitor(listener: (Int) -> Unit) {
        if (isStart) {
            return
        }
        mListener = listener
        Choreographer.getInstance().postFrameCallback(monitorFrameCallback)
        isStart = true
    }

    fun stopMonitor() {
        isStart = false
        Choreographer.getInstance().removeFrameCallback { monitorFrameCallback }
    }

    class MonitorFrameCallback : Choreographer.FrameCallback {

        private val refreshRate by lazy {
            //计算刷新率 赋值给refreshRate
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                App.getInstance().display?.refreshRate ?: 16.6f
            } else {
                val windowManager =
                    App.getInstance().getSystemService(Context.WINDOW_SERVICE) as WindowManager
                windowManager.defaultDisplay.refreshRate
            }
        }

        override fun doFrame(frameTimeNanos: Long) {
            mLastTime = if (mLastTime == 0L) {
                frameTimeNanos
            } else {
                //frameTimeNanos的单位是纳秒,这里需要计算时间差,然后转成毫秒
                val time = (frameTimeNanos - mLastTime) / 1000000
                //跳过了多少帧  //注：1000f/refreshRate 理解为"16ms"
                val frames = (time / (1000f / refreshRate)).toInt()
                if (frames > 1) {
                    mListener.invoke(frames)
                }
                frameTimeNanos
            }
            Choreographer.getInstance().postFrameCallback(this)
        }

    }
}