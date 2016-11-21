package com.exmaple.devtoolindex.monitor.controller;

import com.exmaple.devtoolindex.monitor.cache.ResultCache;
import com.exmaple.devtoolindex.monitor.entity.LineChartResult;
import com.exmaple.devtoolindex.monitor.entity.Result;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@RestController
@RequestMapping("/monitor")
@Slf4j
public class MonitorController {

    @Autowired
    private ResultCache resultCache;

    @RequestMapping("")
    @ResponseBody
    public Collection<Result> getaAll() {
        return resultCache.getAll();
    }

    @RequestMapping("/lineChartData")
    @ResponseBody
    public LineChartResult getLineChartData() {

        LineChartResult lineChartResult = new LineChartResult();
        DateTime rightTime = getRightTime();
        log.info("rightTime:" + rightTime);
        DateTime dt = rightTime.minusDays(1);
        DateTime end = rightTime;


        while (dt.getMillis() <= end.getMillis()) {
            dt = dt.plusSeconds(60);
            // yesterday
            Result r1 = getResult(dt.minusDays(1));
            // today
            Result r2 = getResult(dt);
            if (r1 != null || r2 != null) {
                lineChartResult.addRowData(dt.getMillis(), getResponseTime(r1), getResponseTime(r2));
            }
        }
        return lineChartResult;
    }

    private DateTime getRightTime() {
        DateTime now = new DateTime();
        return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(),
                now.getHourOfDay(), now.getMinuteOfHour() - (now.getMinuteOfHour() % 2),
                0, 0);
    }

    private Long getResponseTime(Result r) {
        return r == null ? -1 : r.getResponseTime();
    }

    private Result getResult(DateTime dt) {
        return resultCache.get(dt.toDate());
    }
}
