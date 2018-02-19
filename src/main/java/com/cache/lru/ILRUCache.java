package com.cache.lru;

public interface ILRUCache<K, V> {

    V put(K key, V value);

    V get(K key);

    int getSpaceLeft();

    int getCapacity();

    int getReadTime();

    int getWriteTime();

}
