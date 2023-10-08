package com.shorten;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCacheExample {
    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(100) // 设置最大缓存大小
            .build();

        // 将数据放入缓存
        cache.put("key1", "value1");

        // 从缓存中读取数据
        String value = cache.getIfPresent("key1");
        System.out.println("Value: " + value); // 输出: Value: value1
    }
}
