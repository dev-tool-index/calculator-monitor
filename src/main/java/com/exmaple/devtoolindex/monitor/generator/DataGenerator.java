package com.exmaple.devtoolindex.monitor.generator;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@Component
public class DataGenerator {
    private Random random = new Random();

    public int getInt(int bound) {
        return random.nextInt(bound);
    }
}
