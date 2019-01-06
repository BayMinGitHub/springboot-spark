package com.bay.sparkspringboot.sparkdemo.service

import org.springframework.stereotype.Service
import java.io.Serializable
import java.util

import org.apache.spark.sql.SparkSession
import org.springframework.beans.factory.annotation.Autowired

import collection.JavaConversions._

/**
  * Author by BayMin, Date on 2018/12/7.
  */
@Service class WordCountService extends Serializable {
  //  @Autowired
  //  val sc: SparkContext = null
  @Autowired
  val ss: SparkSession = null

  def run(filePath: String): util.Map[String, Integer] = {
    val sc = ss.sparkContext
    val scalaMap: Map[String, Int] = sc.textFile(filePath).flatMap(_.split("[ ,\",<,>]")).map((_, 1)).reduceByKey(_ + _).collectAsMap().toMap
    mapAsJavaMap(scalaMap).asInstanceOf[util.Map[String, Integer]]
  }
}