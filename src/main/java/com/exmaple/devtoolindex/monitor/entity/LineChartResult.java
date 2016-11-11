package com.exmaple.devtoolindex.monitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineChartResult {

    @Getter
    private List<RowData> rows = new ArrayList<>();
    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "EST")
    private Date date1;
    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "EST")
    private Date date2;

    public void addRowData(int hour,
                           int minute,
                           int second,
                           long y1,
                           long y2) {
        rows.add(new RowData(hour, minute, second, y1, y2));
    }


    private class RowData {

        @Getter private int hour;
        @Getter private int minute;
        @Getter private int second;
        @Getter private long y1;
        @Getter private long y2;

        private RowData(int hour, int minute, int second, long y1, long y2) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}
