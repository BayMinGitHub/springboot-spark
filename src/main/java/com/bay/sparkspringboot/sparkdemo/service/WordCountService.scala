package com.bay.sparkspringboot.sparkdemo.service

/**
  * Author by BayMin, Date on 2018/12/7.
  */

import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.FileNotFoundException
import java.io.Serializable
import java.util

import org.apache.spark.SparkContext
import org.springframework.beans.factory.annotation.Autowired

import collection.JavaConversions._


/**
  * Author by BayMin, Date on 2018/12/7.
  */
@Service class WordCountService extends Serializable {
  @Autowired
  val sc: SparkContext = null

  @throws[FileNotFoundException]
  def run: util.Map[String, Integer] = {
    val file = ResourceUtils.getFile("classpath:blsmy.txt")
    val line = sc.textFile(file.getAbsolutePath)
    val scalaMap: Map[String, Int] = line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collectAsMap().toMap
    val javaMap: util.Map[String, Integer] = mapAsJavaMap(scalaMap).asInstanceOf[util.Map[String, Integer]]
    javaMap
  }
}

