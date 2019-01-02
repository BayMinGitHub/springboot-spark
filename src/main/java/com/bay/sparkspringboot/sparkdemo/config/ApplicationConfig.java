package com.bay.sparkspringboot.sparkdemo.config;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Author by BayMin, Date on 2018/12/7.
 */
@Configuration
public class ApplicationConfig {
    @Autowired
    private Environment environmen;

    @Value("${spark.app.name}")
    private String appName;

    @Value("${spark.local}")
    private Boolean isLocal;

    @Bean
    public SparkConf sparkConf() {
        if (isLocal)
            return new SparkConf().setAppName(appName).setMaster("local");
        else
            return new SparkConf().setAppName(appName);
    }

    @Bean
    public SparkSession sparkSession() {
        if (isLocal)
            return SparkSession.builder().appName("SparkSQLOnGreenPlum").master("local").getOrCreate();
        else
            return SparkSession.builder().appName("SparkSQLOnGreenPlum").getOrCreate();
    }
//    @Bean
//    public JavaSparkContext javaSparkContext() {
//        return new JavaSparkContext(sparkConf());
//    }

    @Bean
    public SparkContext sparkContext() {
        return new SparkContext(sparkConf());
    }
}
