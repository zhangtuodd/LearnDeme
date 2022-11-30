package design_mode.factory

/**
 * 以登陆为例子，结合工厂模式（工厂方法） + 代理模式 实现登陆
场景如下：实际登陆是可能是qq、微信、手机号登陆
设计如下：登陆的调用者 委托 代理类登陆，代理类在处理一些合法校验逻辑通过后 通过工厂模式来创建对应qq、微信、手机号类的对象进行登陆，并把结果返回
 *
 * @author zhangtuo
 * @date 2022/11/30
 */
object RealCase {
    @JvmStatic
    fun main(args: Array<String>) {
        //实际场景中 调用处应该在vm中，根据返回状态通过livedata去更新UI
        Client.loginPhone(object : CallBack {
            override fun loginSuccess() {
                println("手机号方式执行登陆了，success！！")
            }

            override fun loginFail() {
                println("手机号方式执行登陆了，fail！！")
            }
        })
        Client.loginQQ(object : CallBack {
            override fun loginSuccess() {
                println("QQ方式执行登陆了，success！！")
            }

            override fun loginFail() {
                println("QQ方式执行登陆了，fail！！")
            }
        })
        Client.loginWx(object : CallBack {
            override fun loginSuccess() {
                println("微信方式执行登陆了，success！！")
            }

            override fun loginFail() {
                println("微信方式执行登陆了，fail！！")
            }
        })
    }
}

interface CallBack {
    fun loginSuccess()
    fun loginFail()
}

/**
 * 代理接口
 */
interface IProxy {
    fun loginPhone(callBack: CallBack)
    fun loginQQ(callBack: CallBack)
    fun loginWx(callBack: CallBack)
}

/**
 * 委托类
 */
object Client : IProxy {
    override fun loginPhone(callBack: CallBack) {
        ProxyImpl().loginPhone(callBack)
    }

    override fun loginQQ(callBack: CallBack) {
        ProxyImpl().loginQQ(callBack)
    }

    override fun loginWx(callBack: CallBack) {
        ProxyImpl().loginWx(callBack)
    }
}

/**
 * 代理类
 */
class ProxyImpl : IProxy {
    override fun loginPhone(callBack: CallBack) {
//        if (合法校验完成)
        println("手机号登陆前相关合法校验完成了")
        PhoneFactory().create().login().let {
            if (it) {
                callBack.loginSuccess()
            } else {
                callBack.loginFail()
            }
        }
        println("")
    }

    override fun loginQQ(callBack: CallBack) {
        //        if (合法校验完成)
        println("QQ登陆前相关合法校验完成了")
        QQFactory().create().login().let {
            if (it) {
                callBack.loginSuccess()
            } else {
                callBack.loginFail()
            }
        }
        println("")
    }

    override fun loginWx(callBack: CallBack) {
        //        if (合法校验完成)
        println("微信登陆前相关合法校验完成了")
        WXFactory().create().login().let {
            if (it) {
                callBack.loginSuccess()
            } else {
                callBack.loginFail()
            }
        }
        println("")
    }
}


interface XLoginCase {
    fun login(): Boolean
}

class WxLoginCase : XLoginCase {
    override fun login(): Boolean {
        // 网络请求执行登陆相关逻辑，返回数据model，这里返回boolean模拟即可
        return true;
    }
}

class QQLoginCase : XLoginCase {
    override fun login(): Boolean {
        // 网络请求执行登陆相关逻辑，返回数据model，这里返回boolean模拟即可
        return false
    }
}

class PhoneLoginCase : XLoginCase {
    override fun login(): Boolean {
        // 网络请求执行登陆相关逻辑，返回数据model，这里返回boolean模拟即可
        return true
    }
}

interface XFactory {
    fun create(): XLoginCase
}

class WXFactory : XFactory {
    override fun create(): XLoginCase {
        return WxLoginCase()
    }
}

class QQFactory : XFactory {
    override fun create(): XLoginCase {
        return QQLoginCase()
    }
}

class PhoneFactory : XFactory {
    override fun create(): XLoginCase {
        return PhoneLoginCase()
    }
}