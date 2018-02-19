package com.cache.lru;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LRUCacheConstructorTest {

    @Spy
    private ILRUCache<Integer, String> lruCache = new LRUCache<>(1, 10, 20);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFieldsSet() {
        assertEquals(1, lruCache.getSpaceLeft());
        assertEquals(1, lruCache.getCapacity());
        assertEquals(10, lruCache.getReadTime());
        assertEquals(20, lruCache.getWriteTime());
    }

}
