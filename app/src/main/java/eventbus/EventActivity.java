package eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhangtuo.learndeme.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-10-29
 */
public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        EventBus.getDefault().register(this);
        XEvent xEvent = new XEvent();
        xEvent.name = "zhangsan";
        EventBus.getDefault().post(xEvent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMethod(XEvent event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
