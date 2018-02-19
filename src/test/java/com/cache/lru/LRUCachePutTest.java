package com.cache.lru;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LRUCachePutTest {

    @Spy
    private ILRUCache<Integer, String> lruCache = new LRUCache<>(1, 10, 20);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSingleItem() {
        lruCache.put(1, "one");
        assertEquals("one", lruCache.get(1));
    }

    @Test
    public void testManyItems() {
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        assertEquals(null, lruCache.get(1));
        assertEquals("two", lruCache.get(2));
    }

}
