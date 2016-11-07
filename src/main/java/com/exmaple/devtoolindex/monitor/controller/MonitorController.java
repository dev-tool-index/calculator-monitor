package com.exmaple.devtoolindex.monitor.controller;

import com.exmaple.devtoolindex.monitor.cache.ResultCache;
import com.exmaple.devtoolindex.monitor.entity.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@RestController @RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private ResultCache resultCache;

    @RequestMapping("")
    @ResponseBody
    public Set<Result> getaAll() {
        return resultCache.getAll();
    }
}
