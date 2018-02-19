package com;

import com.cache.ICacheLayer;
import com.util.InputUtil;
import com.cache.CacheLayer;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    private static ICacheLayer cacheLayer;

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        log.info("You can change service configuration parameters in " + ServiceConfiguration.CONFIGURATION_FILE);

        int levelCount = ServiceConfiguration.getLevelCount();
        int[] levelCapacity = ServiceConfiguration.getLevelCapacity();
        int[] readTime = ServiceConfiguration.getReadTime();
        int[] writeTime = ServiceConfiguration.getWriteTime();

        log.info("Service configuration: Level Count: " + levelCount);
        log.info("Service configuration: Levels Capacity: " + Arrays.toString(levelCapacity));
        log.info("Service configuration: Levels Read Time: " + Arrays.toString(readTime));
        log.info("Service configuration: Levels Write Time: " + Arrays.toString(writeTime));

        if (levelCapacity.length != levelCount || readTime.length != levelCount || writeTime.length != levelCount) {
            log.severe("The number of capacity, read or write time elements do not match the number of levels");
            return;
        }

        cacheLayer = new CacheLayer(levelCount, levelCapacity, readTime, writeTime);

        log.info("Type in: method name,list of parameters");
        log.info("Example: write,j,Jason");
        log.info("Example: write,f,Freddy");
        log.info("Example: read,j");
        log.info("Example: read,f");
        log.info("Example: stat");
        log.info("Example: exit");
        log.info("Application is ready for request >");

        InputUtil.readInput(Main.class.getName());
    }

    public static void write(String key, String value) {
        cacheLayer.write(key, value);
    }

    public static void read(String key) {
        log.info(cacheLayer.read(key));
    }

    public static void stat() {
        cacheLayer.stat();
    }

    public static void exit() {
        System.exit(0);
    }
}
