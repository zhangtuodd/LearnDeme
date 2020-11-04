package client.cloudy.com.acfun;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.config.Router;

import client.cloudy.com.annotation.BindPath;

@Route(path = Router.ACFUN_ACT)
@BindPath(path = Router.ACFUN_ACT)
public class AcFunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfun);
//        handler.sendEmptyMessageDelayed(0, 30);
    }

//    @SuppressLint("HandlerLeak")
//    private static Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            for (int i = 0; i < 100; i++) {
//                String[] a = new String[100000];
//            }
//            handler.sendEmptyMessageDelayed(0, 30);
//        }
//    };
}
