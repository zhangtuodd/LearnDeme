package design_mode.package_observer;

import com.example.base.util.LogUtils;

public class ObserverA implements Observer {

    @Override
    public void update() {
        System.out.println("update-------" + getClass().getSimpleName());
    }
}
