package com.exmaple.devtoolindex.monitor.controller;

import com.exmaple.devtoolindex.monitor.cache.ResultCache;
import com.exmaple.devtoolindex.monitor.entity.LineChartResult;
import com.exmaple.devtoolindex.monitor.entity.Result;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@RestController
@RequestMapping("/monitor")
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
        DateTime dt = new DateTime().withTimeAtStartOfDay();
        DateTime end = dt.plusDays(1).withTimeAtStartOfDay();
        // yesterday
        lineChartResult.setDate1(dt.minusDays(1).toDate());
        // today
        lineChartResult.setDate2(dt.toDate());

        while (dt.getMillis() < end.getMillis()) {
            // yesterday
            Result r1 = getResult(dt.minusDays(1));
            // today
            Result r2 = getResult(dt);
            lineChartResult.addRowData(dt.getHourOfDay(), dt.getMinuteOfHour(), dt.getSecondOfMinute(), getResponseTime(r1), getResponseTime(r2));
            dt = dt.plusSeconds(60);
        }
        return lineChartResult;
    }

    private Long getResponseTime(Result r) {
        return r == null ? -1 : r.getResponseTime();
    }

    private Result getResult(DateTime dt) {
        return resultCache.get(dt.toDate());
    }
}
