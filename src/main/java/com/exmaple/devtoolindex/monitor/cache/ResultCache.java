package com.exmaple.devtoolindex.monitor.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.exmaple.devtoolindex.monitor.entity.Result;
import com.exmaple.devtoolindex.monitor.helper.DateHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@Component
@Slf4j public class ResultCache {

    public static final String KEY_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    @Autowired
    private DateHelper dateHelper;
    private Cache<String, Result> cache = CacheBuilder.newBuilder()
            .maximumSize(20000)
            .expireAfterWrite(7, TimeUnit.DAYS)
            .build();

    public void put(Result result) {
        log.info("---" + dateHelper.date2String(result.getDate(), KEY_PATTERN));
        cache.put(dateHelper.date2String(result.getDate(), KEY_PATTERN), result);
    }

    public Result get(Date date) {
        return cache.getIfPresent(dateHelper.date2String(date, KEY_PATTERN));
    }

    public Collection<Result> getAll() {
        return cache.asMap().values();
    }
}
