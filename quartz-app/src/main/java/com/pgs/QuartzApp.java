package com.pgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Arrays;

@SpringBootApplication
public class QuartzApp {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public static void main(String [] args) {
        ApplicationContext ctx = SpringApplication.run(QuartzApp.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

    }
}
