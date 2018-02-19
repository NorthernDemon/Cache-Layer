package com.cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LRUCache<K, V> implements ILRUCache<K, V> {

    private static final Logger log = Logger.getLogger(LRUCache.class.getName());

    private final int capacity;
    private final int readTime;
    private final int writeTime;
    private final Map<K, V> cache;

    public LRUCache(int capacity, int readTime, int writeTime) {
        this.capacity = capacity;
        this.readTime = readTime;
        this.writeTime = writeTime;
        cache = new LinkedHashMap<>(capacity, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return this.size() > capacity;
            }
        };
    }

    @Override
    public V put(K key, V value) {
        return cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public int getSpaceLeft() {
        return capacity - cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getReadTime() {
        return readTime;
    }

    @Override
    public int getWriteTime() {
        return writeTime;
    }

}
