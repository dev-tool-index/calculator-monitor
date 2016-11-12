package com.exmaple.devtoolindex.monitor.task;

import com.exmaple.devtoolindex.monitor.cache.ResultCache;
import com.exmaple.devtoolindex.monitor.entity.Result;
import com.exmaple.devtoolindex.monitor.generator.DataGenerator;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@Component
@Slf4j
public class CheckServiceTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private DataGenerator dataGenerator;
    private static final int BOUND = 100;

    @Autowired
    private ApplicationContext appCtx;

    @Autowired
    private ResultCache resultCache;

    @Scheduled(cron="* */10 * * * *" )
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        long startTime = System.currentTimeMillis();
        int arg1 = dataGenerator.getInt(BOUND);
        int arg2 = dataGenerator.getInt(BOUND);

        Result result = (Result) appCtx.getBean("result", arg1, arg2);

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://calculator-service.herokuapp.com/calc/add")
                    .header("accept", "application/json")
                    .queryString("arg1", result.getArg1())
                    .queryString("arg2", result.getArg2())
                    .asJson();
            long endTime = System.currentTimeMillis();
            result.setStatusCode(jsonResponse.getStatus());
            JSONObject jsonObject = jsonResponse.getBody().getObject();
            result.setResult(jsonObject.getInt("result"));
            result.setCorrect(arg1 + arg2 == result.getResult());
            result.setResponseTime(endTime - startTime);
            result.setDate(new Date(startTime));
        } catch (UnirestException | JSONException e) {
            String msg = e.getMessage();
            result.setErrorMessage(msg);
            log.error(msg, e);
        } finally {
            resultCache.put(result);
        }
    }
}
