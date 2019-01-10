package com.bay.sparkspringboot.sparkdemo.service

import java.util

import com.alibaba.fastjson.JSON
import com.bay.sparkspringboot.sparkdemo.model.TbIUser
import com.bay.sparkspringboot.sparkdemo.util.StringUtil
import org.apache.spark.sql._
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

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

  def run(tableName: String): util.LinkedList[TbIUser] = {
    val list: util.LinkedList[TbIUser] = new util.LinkedList[TbIUser]()
    val frame: DataFrame = ss.read.format("jdbc")
      .option("url", url)
      .option("driver", driver)
      .option("user", user)
      .option("password", password)
      .option("dbtable", tableName).load()
    val jsonSeq: Seq[String] = frame.select("*").limit(50).toJSON.collect().toSeq
    for (jsonString <- jsonSeq) {
      list.push(JSON.parseObject(StringUtil.underlineToHump(new StringBuffer(jsonString)).toString, classOf[TbIUser]))
    }
    list
  }
}
