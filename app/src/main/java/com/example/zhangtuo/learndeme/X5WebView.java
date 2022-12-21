package com.example.zhangtuo.learndeme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.setWebViewClient(client);
        initWebViewSettings();
        this.getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);// 设置允许JS弹窗
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }

}
