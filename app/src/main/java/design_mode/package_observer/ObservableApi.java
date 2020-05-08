package design_mode.package_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题
 */
public abstract class ObservableApi {
    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    protected List<Observer> observers = new ArrayList<>();

    public abstract void add(Observer observer);

    public abstract void remove(Observer observer);

    public abstract void updateAll();


    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
