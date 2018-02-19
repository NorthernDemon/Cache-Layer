package com;

import com.cache.CacheLayerTest;
import com.cache.lru.LRUCacheTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CacheLayerTest.class,
        LRUCacheTest.class
})
public class AllTest {
}
