package com.exmaple.devtoolindex.monitor.entity;

import com.exmaple.devtoolindex.monitor.generator.DataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hongkailiu on 2016-11-06.
 */
public class Result {

    @Getter private int arg1;
    @Getter private int arg2;
    @Getter @Setter private long responseTime;
    @Getter @Setter private boolean isCorrect;
    @Getter @Setter private String errorMessage;
    @Getter @Setter private int statusCode;
    @Getter @Setter private int result;


    public Result(int arg1, int arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
