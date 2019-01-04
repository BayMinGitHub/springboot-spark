package com.bay.sparkspringboot.sparkdemo.service

import java.util

import com.bay.sparkspringboot.sparkdemo.util.IpParserUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import collection.JavaConversions._

/**
  * Author by BayMin, Date on 2018/12/21.
  */
@Service class GetThingService {
  @Autowired
  val sc: SparkContext = null

  def getSay(filePath: String): util.Map[String, String] = {
    val logData: RDD[String] = sc.textFile(filePath)
    val scalaMap: Map[String, String] = logData.filter(_.contains("say")).map(line => {
      val data = line.substring(0, line.indexOf("say"))
      val whoSay = data.substring(0, data.indexOf(">", data.indexOf("STEAM")) + 1)
      val sayWhat = line.substring(line.indexOf("say"), line.length)
      (whoSay, sayWhat)
    }).collectAsMap().toMap
    mapAsJavaMap(scalaMap)
  }

  def getIp(filePath: String): util.Map[String, String] = {
    val logData: RDD[String] = sc.textFile(filePath)
    val scalaMap: Map[String, String] = logData.filter(_.contains("address")).filter(_.contains("STEAM")).filter(!_.contains("<BOT>")).map(line => {
      val data = line.split(" ")
      val date = data(1)
      val time = data(3)
      val ip = data(data.length - 1).split(":")(0).replace("\"", "")
      val name = line.substring(line.indexOf(": \"") + 3, line.indexOf("<", line.indexOf(": \"") + 3))
      val steamId = line.substring(line.indexOf("<STEAM_") + 1, line.indexOf(">", line.indexOf("<STEAM_")))
      (date, time, name, steamId, ip)
    }).map(data => {
      (data._3 + " " + data._4, data._5)
    }).distinct().map(data => {
      val who = data._1
      val ip = data._2
      (who, new IpParserUtil().parserIpByIpSeeker(ip).toString)
    }).collectAsMap().toMap
    mapAsJavaMap(scalaMap)
  }
}
