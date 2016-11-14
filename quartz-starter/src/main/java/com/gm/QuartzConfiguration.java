package com.gm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

/**
 * Created by gmurias on 13.11.16.
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class QuartzConfiguration {
    @Autowired
    DataSource dataSource;

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource /*, JobFactory jobFactory,
                                                     @Qualifier("sampleJobTrigger") Trigger sampleJobTrigger*/) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }
    //https://github.com/davidkiss/spring-boot-quartz-demo/blob/master/src/main/java/com/kaviddiss/bootquartz/SchedulerConfig.java
}
