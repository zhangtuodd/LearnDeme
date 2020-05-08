package design_mode.package_observer;

/**
 * 具体主题
 */
public class Observable extends ObservableApi {

    @Override
    public void add(Observer observer) {
        if (observer == null) {
            return;
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void remove(Observer observer) {
        if (observer == null) {
            return;
        }
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void updateAll() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }

}
