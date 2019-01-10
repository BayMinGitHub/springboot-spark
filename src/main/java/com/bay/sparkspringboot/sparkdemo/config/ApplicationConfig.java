package com.bay.sparkspringboot.sparkdemo.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author by BayMin, Date on 2018/12/7.
 */
@Configuration
public class ApplicationConfig {
    @Value("${spark.app.name}")
    private String appName;

    @Value("${spark.local}")
    private Boolean isLocal;

    @Bean
    public SparkSession sparkSession() {
        if (isLocal)
            return SparkSession.builder().appName(appName).master("local").getOrCreate();
        else
            return SparkSession.builder().appName(appName).getOrCreate();
    }
}
