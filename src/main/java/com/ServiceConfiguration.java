package com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Reads property file with service configuration
 */
public abstract class ServiceConfiguration {

    private static final Logger log = Logger.getLogger(ServiceConfiguration.class.getName());

    public static final String CONFIGURATION_FILE = "service.properties";

    private static int levelCount;

    private static int[] levelCapacity;

    private static int[] readTime;

    private static int[] writeTime;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(CONFIGURATION_FILE));

            levelCount = Integer.parseInt(properties.getProperty("level_count"));
            levelCapacity = getArray(properties, "level_capacity");
            readTime = getArray(properties, "read_time");
            writeTime = getArray(properties, "write_time");
        } catch (IOException e) {
            log.severe("Failed to load service configuration!");
            e.printStackTrace();
        }
    }

    private static int[] getArray(Properties properties, String key) {
        String property = properties.getProperty(key);
        String[] list = property.split(",");
        int[] result = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = Integer.parseInt(list[i]);
        }
        return result;
    }

    public static int getLevelCount() {
        return levelCount;
    }

    public static int[] getLevelCapacity() {
        return levelCapacity;
    }

    public static int[] getReadTime() {
        return readTime;
    }

    public static int[] getWriteTime() {
        return writeTime;
    }
}
