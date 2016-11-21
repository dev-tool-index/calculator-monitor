package com.exmaple.devtoolindex.monitor.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
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
    @Getter
    private String title1 = "last 48 - 24 hours";
    @Getter
    private String title2 = "last 24 hours - now";

    public void addRowData(long time,
                           long y1,
                           long y2) {
        rows.add(new RowData(time, y1, y2));
    }


    private class RowData {
        @Getter
        private long time;
        @Getter
        private long y1;
        @Getter
        private long y2;

        private RowData(long time, long y1, long y2) {
            this.time = time;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}
