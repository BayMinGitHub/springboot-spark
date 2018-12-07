package com.bay.sparkspringboot.sparkdemo.config;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf().setAppName("spark-springboot").setMaster("local");
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
