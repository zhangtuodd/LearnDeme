package activity;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/7/4
 */

public class DragZoomImageActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_zoom_image_view);
    }
}
