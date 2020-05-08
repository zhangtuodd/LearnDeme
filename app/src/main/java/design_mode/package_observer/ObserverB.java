package design_mode.package_observer;


public class ObserverB implements Observer {

    @Override
    public void update() {
        System.out.println("update-------" + getClass().getSimpleName());
    }
}
