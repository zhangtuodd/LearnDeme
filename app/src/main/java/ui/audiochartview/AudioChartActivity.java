package ui.audiochartview;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.zhangtuo.learndeme.R;

/**
 * Created by zhangtuo on 2017/10/27.
 */

public class AudioChartActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_chart_view);
    }
}
