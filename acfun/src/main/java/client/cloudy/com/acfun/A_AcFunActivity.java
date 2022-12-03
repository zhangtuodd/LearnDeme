package client.cloudy.com.acfun;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.config.Router;

import client.cloudy.com.annotation.BindPath;

@Route(path = Router.A_ACFUN_ACT)
@BindPath(path = Router.A_ACFUN_ACT)
public class A_AcFunActivity extends AppCompatActivity implements Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfun);
//        createMemoryLeak();
        handler.sendEmptyMessageDelayed(0, 30);
    }

//    /**
//     * 反复进入推出act
//     */
//    private void createMemoryLeak() {
//        ImageView imageView = findViewById(R.id.iv);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.common_ic_holder);
//        imageView.setImageBitmap(bitmap);
//
//        CallbackManager.add(this);
//    }
//
    @Override
    public void operate() {
        //do sth
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        CallbackManager.remove(this);
//    }


        @SuppressLint("HandlerLeak")
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < 100; i++) {
                String[] a = new String[100000];
            }
            handler.sendEmptyMessageDelayed(0, 30);
        }
    };
}
