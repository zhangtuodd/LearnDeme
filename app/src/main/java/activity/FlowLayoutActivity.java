package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.base.event.OneEvent;
import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);
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

    @Subscribe(sticky = true)
    public void eventCallback(OneEvent oneEvent) {
        Toast.makeText(this, "--" + oneEvent.name, Toast.LENGTH_LONG).show();
        OneEvent oneEvent1 = EventBus.getDefault().getStickyEvent(OneEvent.class);
        if(oneEvent1 != null){
            EventBus.getDefault().removeStickyEvent(OneEvent.class);
            LogUtils.i("zhangsan","oneEvent1-------"+oneEvent1.toString());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
