package com.bay.sparkspringboot.sparkdemo.service

import java.util

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Author by BayMin, Date on 2018/12/21.
  */
@Service class FilterSteamService {
  @Autowired
  val sc: SparkContext = null

  def run(filePath: String): util.Map[String, Integer] = {
    val logData: RDD[String] = sc.textFile(filePath)

    null
  }
}
