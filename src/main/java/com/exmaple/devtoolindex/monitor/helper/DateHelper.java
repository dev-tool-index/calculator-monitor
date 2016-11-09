package com.exmaple.devtoolindex.monitor.helper;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongkailiu on 2016-11-08.
 */
@Component
public class DateHelper {
    public String date2String(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
