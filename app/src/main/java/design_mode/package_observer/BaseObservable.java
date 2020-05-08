package design_mode.package_observer;

import com.example.zhangtuo.learndeme.BuildConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseObservable<T> {

    /**
     * The list of observers.  An observer can be in the list at most
     * once and will never be null.
     */
    private final ArrayList<T> mObservers = new ArrayList<>();

    /**
     * Adds an observer to the list. The observer cannot be null and it must not already
     * be registered.
     *
     * @param observer the observer to register
     * @throws IllegalArgumentException the observer is null
     * @throws IllegalStateException    the observer is already registered
     */
    public void registerObserver(T observer) {
        if (observer == null) {
            if (BuildConfig.DEBUG) {
                throw new IllegalArgumentException("The observer is null.");
            } else {
                return;
            }
        }
        synchronized (mObservers) {
            if (mObservers.contains(observer)) {
                if (BuildConfig.DEBUG) {
                    throw new IllegalStateException("Observer " + observer + " is already registered.");
                } else {
                    return;
                }
            }
            mObservers.add(observer);
        }
    }

    /**
     * Removes a previously registered observer. The observer must not be null and it
     * must already have been registered.
     *
     * @param observer the observer to unregister
     * @throws IllegalArgumentException the observer is null
     * @throws IllegalStateException    the observer is not yet registered
     */
    public void unregisterObserver(T observer) {
        if (observer == null) {
            if (BuildConfig.DEBUG) {
                throw new IllegalArgumentException("The observer is null.");
            } else {
                return;
            }
        }
        synchronized (mObservers) {
            int index = mObservers.indexOf(observer);
            if (index == -1) {
                if (BuildConfig.DEBUG) {
                    throw new IllegalStateException("Observer " + observer + " was not registered.");
                } else {
                    return;
                }
            }
            mObservers.remove(index);
        }
    }

    /**
     * Remove all registered observers.
     */
    public void unregisterAll() {
        synchronized (mObservers) {
            mObservers.clear();
        }
    }


    public interface INotifyCallback<T> {
        void onNotify(T listener);
    }

    public void startNotify(INotifyCallback<T> callback) {
        List<T> list = getObservers();
        for (Iterator<T> iterator = list.iterator(); iterator.hasNext(); ) {
            T listenerItem = iterator.next();
            if (listenerItem != null) {
                try {
                    callback.onNotify(listenerItem);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get all observers.
     */
    public List<T> getObservers() {
        synchronized (mObservers) {
            return new ArrayList<>(mObservers);
        }
    }
}
