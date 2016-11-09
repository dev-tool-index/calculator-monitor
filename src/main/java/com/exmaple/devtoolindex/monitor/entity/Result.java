package com.exmaple.devtoolindex.monitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    @Getter
    private int arg1;
    @Getter
    private int arg2;
    @Getter
    @Setter
    private long responseTime;
    @Getter
    @Setter
    private boolean isCorrect;
    @Getter
    @Setter
    private String errorMessage;
    @Getter
    @Setter
    private int statusCode;
    @Getter
    @Setter
    private int result;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "EST")
    @Getter
    @Setter
    private Date date;

    public Result(int arg1, int arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public static class NullResult extends Result {

        public NullResult() {
            super(-1, -1);
            this.setResponseTime(-1);
        }
    }
}
