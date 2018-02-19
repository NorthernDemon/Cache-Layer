package com.cache;

import com.cache.lru.ILRUCache;
import com.cache.lru.LRUCache;
import com.google.common.collect.EvictingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class CacheLayer implements ICacheLayer {
    
    private static final Logger log = Logger.getLogger(CacheLayer.class.getName());

    private static final int AVERAGE_COUNT = 10;

    private final int levelCount;
    private final List<ILRUCache<String, String>> cacheLayers;

    private final Queue<Integer> reads = EvictingQueue.create(AVERAGE_COUNT);
    private final Queue<Integer> writes = EvictingQueue.create(AVERAGE_COUNT);

    public CacheLayer(int levelCount, int[] levelCapacity, int[] readTime, int[] writeTime) {
        this.levelCount = levelCount;
        this.cacheLayers = new ArrayList<>(levelCount);
        for (int i = 0; i < levelCount; i++) {
            cacheLayers.add(new LRUCache<>(levelCapacity[i], readTime[i], writeTime[i]));
        }
    }

    @Override
    public String read(String key) {
        boolean isUpdated = true;
        log.info("Reading: " + key);
        int totalReadTime = 0;
        for (int i = 0; i < levelCount; i++) {
            log.info("Reading cache level " + (i + 1));
            ILRUCache<String, String> cache = cacheLayers.get(i);
            int readTime = cache.getReadTime();
            reads.add(readTime);
            totalReadTime += readTime;
            String value = cache.get(key);
            if (value != null) {
                log.info("Read time: " + totalReadTime);
                if (!isUpdated) {
                    log.info("Updating the cache to upper levels");
                    write(key, value);
                }
                return value;
            } else {
                isUpdated = false;
            }
        }
        log.info("Read time: " + totalReadTime);
        return null;
    }

    @Override
    public void write(String key, String value) {
        log.info("Writing: " + key + " => " + value);
        int totalWriteTime = 0;
        for (int i = 0; i < levelCount; i++) {
            ILRUCache<String, String> cache = cacheLayers.get(i);
            String oldValue = cache.get(key);
            if (oldValue == null || !oldValue.equals(value)) {
                log.info("Writing cache level " + (i + 1));
                int writeTime = cache.getWriteTime();
                writes.add(writeTime);
                totalWriteTime += writeTime;
                cache.put(key, value);
            } else {
                log.info("Write time: " + totalWriteTime);
                return;
            }
        }
        log.info("Write time: " + totalWriteTime);
    }

    @Override
    public void stat() {
        for (int i = 0; i < levelCount; i++) {
            log.info("Level " + (i + 1) + " has " + cacheLayers.get(i).getSpaceLeft() + " space(s) left");
        }
        log.info("Average of the last " + AVERAGE_COUNT + " reads: " + getAverageOfLastN(reads));
        log.info("Average of the last " + AVERAGE_COUNT + " writes: " + getAverageOfLastN(writes));
    }

    private float getAverageOfLastN(Queue<Integer> listOfInts) {
        return (float) listOfInts.stream().mapToInt(i -> i).sum() / AVERAGE_COUNT;
    }

}
