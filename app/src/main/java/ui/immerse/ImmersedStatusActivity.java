package ui.immerse;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.zhangtuo.learndeme.R;

/**
 * Created by zhangtuo on 2017/10/13.
 */

public class ImmersedStatusActivity extends Activity {
    private CompatToolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViewRefreTOStatus();

        setContentView(R.layout.immerseed_view);
        toolbar = (CompatToolbar) findViewById(R.id.toolbar);
        toolbar.setCustomClickListener(new CustomClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void setViewRefreTOStatus() {
        // 5.0以上系统状态栏全透明
        // 4.4以上时不同手机型号（华为小米魅族）不同版本的sdk显示也不一样(全透明，半透明，渐变透明)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//半透明
        }
    }
}
