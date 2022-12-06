package binder.server

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.RemoteException
import com.example.zhangtuo.learndeme.IAidlInterface
import android.widget.Toast

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/12/6
 */
internal class MyService : Service() {
    override fun onBind(intent: Intent): IBinder {
        return stub  //得到binder的代理对象
    }

    var stub: IAidlInterface.Stub = object : IAidlInterface.Stub() {
        @Throws(RemoteException::class)
        override fun login(name: String, pwd: String) {
           Handler(Looper.getMainLooper()).post {
               Toast.makeText(this@MyService, "login success", Toast.LENGTH_SHORT).show()
           }
        }

        @Throws(RemoteException::class)
        override fun loginCallBack(): String {
            return "token 001"
        }
    }
}