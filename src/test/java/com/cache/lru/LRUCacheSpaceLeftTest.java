package com.cache.lru;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LRUCacheSpaceLeftTest {

    @Spy
    private ILRUCache<Integer, String> lruCache = new LRUCache<>(1, 10, 20);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIfCapacityIsEmpty() {
        assertEquals(0, new LRUCache<>(0, 10, 20).getSpaceLeft());
    }

    @Test
    public void testIfCapacityIsNonEmpty() {
        assertEquals(1, lruCache.getSpaceLeft());
    }

    @Test
    public void testWhenSizeIsEqualThanCapacity() {
        lruCache.put(1, "one");
        assertEquals(0, lruCache.getSpaceLeft());
    }

    @Test
    public void testWhenSizeIsLessThanCapacity() {
        LRUCache<Object, Object> cache = new LRUCache<>(2, 10, 20);
        cache.put(1, "one");
        assertEquals(1, lruCache.getSpaceLeft());
    }

}
