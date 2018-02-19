package com.cache.lru;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LRUCacheGetTest {

    @Spy
    private ILRUCache<Integer, String> lruCache = new LRUCache<>(1, 10, 20);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNonExistingItem() {
        assertEquals(null, lruCache.get(1));
    }

    @Test
    public void testNonExistingItemInNonEmptyCache() {
        lruCache.put(1, "one");
        assertEquals(null, lruCache.get(2));
    }

    @Test
    public void testExistingItemInNonEmptyCache() {
        lruCache.put(1, "one");
        assertEquals("one", lruCache.get(1));
    }

}
