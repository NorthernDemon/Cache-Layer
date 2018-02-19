package com.cache.lru;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LRUCacheConstructorTest.class,
        LRUCacheSpaceLeftTest.class,
        LRUCachePutTest.class,
        LRUCacheGetTest.class,
        LRUCacheRemoveOldestTest.class
})
public class LRUCacheTest {
}
