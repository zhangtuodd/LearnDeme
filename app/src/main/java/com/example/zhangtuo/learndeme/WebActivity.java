package com.example.zhangtuo.learndeme;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebViewClient;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/6/8
 */

public class WebActivity extends BaseActivity {

    private X5WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        webView = (X5WebView) findViewById(R.id.web_view);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        webView.loadUrl("http://www.yuedongqc.com/app/index.php?i=2&c=entry&m=xyshopx&do=mobile");



    }

}
