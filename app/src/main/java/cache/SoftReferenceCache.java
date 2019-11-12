package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author： zhangtuo
 * @date： 2019-11-07
 * @description：
 */
public class SoftReferenceCache<K, V> {
    private final HashMap<K, SoftReference<V>> mCache;

    public SoftReferenceCache() {
        mCache = new HashMap<K, SoftReference<V>>();
    }


    /**
     * 将对象放进缓存中，这个对象可以在GC发生时被回收
     *
     * @param key   key的值.
     * @param value value的值型.
     */
    public void put(K key, V value) {
        mCache.put(key, new SoftReference<V>(value));
    }


    /**
     * 从缓存中获取value
     *
     * @param key
     * @return 如果找到的话返回value，如果被回收或者压根儿没有就返* 回null
     */
    public V get(K key) {
        V value = null;
        SoftReference<V> reference = mCache.get(key);

        if (reference != null) {
            value = reference.get();
        }
        return value;
    }

}
