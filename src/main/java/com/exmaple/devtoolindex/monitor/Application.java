package com.exmaple.devtoolindex.monitor;

import com.exmaple.devtoolindex.monitor.entity.Result;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * Created by hongkailiu on 2016-11-06.
 */
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Result result(int arg1, int arg2) {
        return new Result(arg1, arg2);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
