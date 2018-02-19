package com.cache.lru;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LRUCacheRemoveOldestTest {

    @Spy
    private ILRUCache<Integer, String> lruCache = new LRUCache<>(3, 10, 20);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnManyEntriesInOrder() {
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        lruCache.put(3, "three");
        lruCache.put(4, "four");
        lruCache.put(5, "five");
        assertEquals(null, lruCache.get(1));
        assertEquals(null, lruCache.get(2));
        assertEquals("three", lruCache.get(3));
        assertEquals("four", lruCache.get(4));
        assertEquals("five", lruCache.get(5));
    }

    @Test
    public void testOnManyEntriesOutOfOrder() {
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        lruCache.put(3, "three");
        lruCache.get(3);
        lruCache.get(2);
        lruCache.get(1);
        lruCache.put(4, "four");
        lruCache.put(5, "five");
        assertEquals("one", lruCache.get(1));
        assertEquals(null, lruCache.get(2));
        assertEquals(null, lruCache.get(3));
        assertEquals("four", lruCache.get(4));
        assertEquals("five", lruCache.get(5));
    }

}
