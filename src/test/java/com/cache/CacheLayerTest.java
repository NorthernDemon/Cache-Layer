package com.cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CacheLayerTest {

    private int levelCount = 3;
    private int[] levelCapacity = {3, 4, 5};
    private int[] readTime = {5, 10, 15};
    private int[] writeTime = {10, 20, 30};

    @Spy
    private ICacheLayer cacheLayer = new CacheLayer(levelCount, levelCapacity, readTime, writeTime);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReadWhenEmpty() {
        assertEquals(null, cacheLayer.read("q"));
    }

    @Test
    public void testReadWhenNonEmptyFound() {
        cacheLayer.write("q", "qq");
        assertEquals("qq", cacheLayer.read("q"));
    }

    @Test
    public void testReadWhenNonEmptyNotFound() {
        cacheLayer.write("q", "qq");
        assertEquals(null, cacheLayer.read("v"));
    }

}
