package com.bay.sparkspringboot.sparkdemo.service

import java.util

import com.alibaba.fastjson.JSON
import com.bay.sparkspringboot.sparkdemo.model.TbIUser
import com.bay.sparkspringboot.sparkdemo.util.StringUtil
import org.apache.spark.sql._
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

import scala.collection.mutable.ListBuffer
import collection.JavaConversions._

/**
  * Author by BayMin, Date on 2018/12/29.
  */
@Service class SQLDemoService {
  @Value("${greenplum.url}") private val url: String = null
  @Value("${greenplum.driver_class}") private val driver: String = null
  @Value("${greenplum.username}") private val user: String = null
  @Value("${greenplum.password}") private val password: String = null

  @Autowired
  val ss: SparkSession = null

  def run(tableName: String, reqSid: String): TbIUser = {
    val frame: DataFrame = ss.read.format("jdbc")
      .option("url", url)
      .option("driver", driver)
      .option("user", user)
      .option("password", password)
      .option("dbtable", tableName).load()
    val jsonSeq: Seq[String] = frame.where("req_sid = '" + reqSid + "'").select("*").limit(1).toJSON.collect().toSeq
    // val list: ListBuffer[TbIUser] = new ListBuffer[TbIUser]
    // var tbIUser: TbIUser = null
    JSON.parseObject(StringUtil.underlineToHump(new StringBuffer(jsonSeq.get(0))).toString, classOf[TbIUser])
    // for (jsonString <- jsonSeq.toArray) {
    //  return JSON.parseObject(jsonString.toString, classOf[TbIUser])
    // }
    // println(tbIUser.getLogTime)
    // tbIUser
    // bufferAsJavaList(list)
    // reader.select("req_sid").limit(1).write.mode("overwrite").saveAsTable("test")
    // val frame = ss.sql("select * from test")
  }
}
