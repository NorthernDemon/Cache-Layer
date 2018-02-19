package com.cache;

public interface ICacheLayer {

    String read(String key);

    void write(String key, String value);

    void stat();

}
