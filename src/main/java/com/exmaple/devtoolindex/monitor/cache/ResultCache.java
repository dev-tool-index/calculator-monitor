package com.exmaple.devtoolindex.monitor.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.exmaple.devtoolindex.monitor.entity.Result;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@Component
public class ResultCache {

    private Cache<Result, Integer> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(7, TimeUnit.DAYS)
            .build();

    public void put(Result result){
        cache.put(result, 0);
    }

    public Set<Result> getAll(){
        return cache.asMap().keySet();
    }
}
