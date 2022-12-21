package webview

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.widget.Button
import android.widget.Toast
import com.example.zhangtuo.learndeme.App
import com.example.zhangtuo.learndeme.BaseActivity
import com.example.zhangtuo.learndeme.R
import com.example.zhangtuo.learndeme.X5WebView
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/12/21
 *
 * https://juejin.cn/post/6844904153605505032#heading-6
 */
class WebviewActivity:BaseActivity() {

    private var mWebView: X5WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_layout)


        mWebView = findViewById<View>(R.id.web_view) as X5WebView
        mWebView!!.loadUrl("file:///android_asset/test.html")
        mWebView!!.webChromeClient = WebChromeClient()
        mWebView!!.webViewClient = object : WebViewClient(){

            override fun onPageFinished(p0: WebView?, p1: String?) {
                super.onPageFinished(p0, p1)
                findViewById<Button>(R.id.bt).setOnClickListener{
                    mWebView!!.post {
//               mWebView!!.loadUrl("javascript:javaCallJs()")

                        if (Build.VERSION.SDK_INT < 18) {
                            mWebView!!.loadUrl("javascript:javaCallJs()")
                        } else {
                            mWebView!!.evaluateJavascript("javascript:javaCallJs()", null)
                        }
                    }
                }
            }
        }

        //js调用java，设置映射对象main，添加@JavascriptInterface
        mWebView!!.addJavascriptInterface(JSKit(), "main")

    }

}

public class JSKit {

    // 定义JS需要调用的方法，被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    fun jsCallJava(message: String?) {
        Toast.makeText(App.getInstance(), "$message--js调java", Toast.LENGTH_SHORT).show()
    }
}
