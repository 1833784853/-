package com.imcode.common.model;

import java.util.*;


/**
 * 自动清理map集合里过期的数据
 *
 * @param <K>
 * @param <V>
 */
public class CacheMap<K, V> extends AbstractMap<K, V> {

    private static final long DEFAULT_TIMEOUT = 1000 * 60 * 15; // 数据一分钟后过期
    private static final long CLEAR_TIMEOUT = 1000 * 30;  // 30M检测一次
    private static CacheMap<Object, Object> Instance; // 单例模式，为防止线程创建过多

    public static synchronized final CacheMap<Object, Object> getInstance() {
        if (Instance == null) {
            Instance = new CacheMap<Object, Object>(DEFAULT_TIMEOUT);
        }
        return Instance;
    }

    private class CacheEntry implements Entry<K, V> {

        long time;
        K key;
        V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            return this.value = value;
        }

        public CacheEntry(K key, V value) {
            this.time = System.currentTimeMillis();
            this.key = key;
            this.value = value;
        }
    }

    private class ClearThread extends Thread {
        ClearThread() {
            setName("clear cache thread");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    long now = System.currentTimeMillis();
                    Object[] keys = map.keySet().toArray();
                    for (Object key : keys) {
                        CacheEntry entry = map.get(key);
                        if (now - entry.time >= cacheTimeout) {
                            synchronized (map) {
                                map.remove(key);
                            }
                        }
                    }
                    Thread.sleep(CLEAR_TIMEOUT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private long cacheTimeout;
    private Map<K, CacheEntry> map = new HashMap<K, CacheEntry>();

    public CacheMap(long timeout) {
        this.cacheTimeout = timeout;
        new ClearThread().start();
    }

    @Override
    public V get(Object key) {
        CacheEntry cacheEntry = map.get(key);
        return cacheEntry == null ? null : cacheEntry.value;
    }

    @Override
    public V put(K key, V value) {
        CacheEntry cacheEntry = new CacheEntry(key, value);
        synchronized (map) {
            map.put(key, cacheEntry);
        }
        return value;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<Entry<K, V>>();
        Set<Entry<K, CacheEntry>> entrySet = map.entrySet();
        for (Entry<K, CacheEntry> entry : entrySet) {
            entries.add(entry.getValue());
        }
        return entries;
    }


}
