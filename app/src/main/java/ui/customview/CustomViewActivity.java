package ui.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.example.zhangtuo.learndeme.R;

/**
 * Created by zhangtuo on 2017/10/17.
 */

public class CustomViewActivity extends Activity {

    CircleBarView circleBarView;
    TextView circleNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        circleBarView = (CircleBarView) findViewById(R.id.circle_bar_view);
        circleNum = (TextView) findViewById(R.id.tv);
        circleBarView.setTextView(circleNum);
        //
        circleBarView.setProgressNum(50, 3000);

    }


}
