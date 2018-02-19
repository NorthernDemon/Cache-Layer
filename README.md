# Multiple Level Cache Management System

- Java 9 is required
- The underlying cache policy is [LRU](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_Recently_Used_(LRU))
- Write operation writes the key-value pair into the entire cache starting from the first level if the key does not exist or the value is outdated
- Read operation reads the cache levels in order and returns the first value found
- Read operation invokes the write operation if the value is not found at the first level cache.

# How to configure

Modify this file:

```$xslt
service.properties
```

# How to test

```$xslt
mvn test
```

# How to run

```$xslt
mvn clean package
java -jar target/cache-layer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

The application will be waiting for the user input.

# How to write

```$xslt
write,h,Homer
```

# How to read

```$xslt
read,h
```

# How to stat

```$xslt
stat
```

# How to exit

```$xslt
exit
```
