//package com.bay.sparkspringboot.sparkdemo.service;
//
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ResourceUtils;
//import scala.Tuple2;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
///**
// * Author by BayMin, Date on 2018/12/7.
// */
//@Component
//public class WordCountService implements Serializable {
//    private static final Pattern SPACE = Pattern.compile(" ");
//
//    @Autowired
//    private transient JavaSparkContext sc;
//
//    public Map<String, Integer> run() throws FileNotFoundException {
//        Map<String, Integer> result = new HashMap<>();
//        File file = ResourceUtils.getFile("classpath:blsmy.txt");
//        JavaRDD<String> lines = sc.textFile(file.getAbsolutePath());
//        JavaRDD<String> words = lines.flatMap(word -> Arrays.asList(SPACE.split(word)));
//        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));
//        JavaPairRDD<String, Integer> counts = ones.reduceByKey((Integer i1, Integer i2) -> (i1 + i2));
//        List<Tuple2<String, Integer>> output = counts.collect();
//        output.forEach(item -> result.put(item._1(), item._2()));
//        return result;
//    }
//
//}
