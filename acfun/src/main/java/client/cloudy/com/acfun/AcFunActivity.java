package client.cloudy.com.acfun;

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
    }
}
