package binder.client

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import android.widget.Toast
import com.example.base.util.LogUtils
import com.example.zhangtuo.learndeme.BaseActivity
import com.example.zhangtuo.learndeme.IAidlInterface

/**
 * 结合代码透彻的讲解aidl参考链接：
 * @Link[https://blog.csdn.net/qq_30993595/article/details/78481716]
 * 我的笔记：【AIDL实现及内部原理】
 * https://note.youdao.com/s/GfwF2oCk
 * @author zhangtuo
 * @date 2022/12/6
 */
class AidlActivity : BaseActivity() {

    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtils.e("ServiceConnection", "onServiceConnected---name:${name}")
            /**
             * IBinder是一个接口，代表了跨进程传输能力
             *
             * 这个方法用于将服务端的Binder对象转换成客户端所需的AIDL接口类型的对象
             * 如果客户端和服务端位于同一进程，那么此方法返回的就是服务端的Stub对象本身，因为Stub就是Binder的本地对象
             * 否则返回服务端的Binder代理，也就是内部类Proxy
             *
             * 这就好比你在北京，A商城(Stub)也在北京，你想买A商城的苹果，就直接去本地A商城买东西
             * 但是如果你在西藏，那你只能去A商城在西藏的代理点(Proxy)去买它的苹果
             */
            val asInterface: IAidlInterface = IAidlInterface.Stub.asInterface(service)
            asInterface.login("aa", "aa")
            Toast.makeText(this@AidlActivity,  asInterface.loginCallBack(), Toast.LENGTH_SHORT).show()

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(TextView(this).apply {
            text = "-------------"
        })

        val intent = Intent().apply {
            action = "binder.server.RomoteMyService";
            setClassName(this@AidlActivity,"binder.server.MyService")
        }

        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

}