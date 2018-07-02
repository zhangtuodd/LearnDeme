package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.zhangtuo.learndeme.R;

import java.util.ArrayList;
import java.util.List;

import ui.BlockModel;
import ui.FlowLayout;

/**
 * 流式布局
 *
 * @author zhangtuo
 * @date 2018/5/10
 */

public class FlowLayoutActivity extends Activity {
    LinearLayout layout;
    List<BlockModel> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_layout);
        layout = (LinearLayout) findViewById(R.id.flow);
        FlowLayout flowLayout = new FlowLayout(this);
        for (int i = 0; i < 5; i++) {
            BlockModel model = new BlockModel();
            model.text = "" + i;
            model.isCheck = false;
            list.add(model);
        }
        flowLayout.setData(list);
        flowLayout.setCheckStyle(false);
        layout.addView(flowLayout);
    }
}
