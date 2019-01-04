package com.bay.sparkspringboot.sparkdemo.service

import java.text.SimpleDateFormat
import java.util
import java.util.Date

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
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val map: util.LinkedHashMap[String, String] = new util.LinkedHashMap[String, String]()
    logData.filter(line => {
      line.contains("say") && !line.contains("\"Console<")
    }).map(line => {
      val data = line.split(" ")
      val date = data(1)
      val time = data(3).substring(0, data(3).length - 1)
      val name = line.substring(line.indexOf(": \"") + 3, line.indexOf("<", line.indexOf(": \"") + 3))
      val steamId = line.substring(line.indexOf("<STEAM_") + 1, line.indexOf(">", line.indexOf("<STEAM_")))
      val sayWhat = line.substring(line.indexOf("say"), line.length)
      (new Date(date + " " + time), (name + " " + steamId, sayWhat))
    }).sortByKey().collect().foreach(line => {
      map.put(simpleDateFormat.format(line._1) + " " + line._2._1, line._2._2)
    })
    map
  }

  def getIp(filePath: String): util.Map[String, String] = {
    val logData: RDD[String] = sc.textFile(filePath)
    val scalaMap: Map[String, String] = logData.filter(line => {
      line.contains("address") && line.contains("STEAM") && !line.contains("<BOT>")
    }).map(line => {
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
      (data._1, new IpParserUtil().parserIpByIpSeeker(data._2).toString)
    }).collectAsMap().toMap
    mapAsJavaMap(scalaMap)
  }
}
