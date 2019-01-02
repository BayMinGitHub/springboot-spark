package com.bay.sparkspringboot.sparkdemo

import com.alibaba.fastjson.JSON
import com.bay.sparkspringboot.sparkdemo.model.TbIUser
import com.bay.sparkspringboot.sparkdemo.util.StringUtil

/**
  * Author by BayMin, Date on 2018/12/29.
  */
object JSONTest {
  def main(args: Array[String]): Unit = {
    val str = "{\"req_sid\":\"20171229084654h6nnmi\",\"promotion_id\":\"201712289871\",\"user_id\":9017122963555866,\"serial_number\":\"18644720348\",\"product_id\":\"90275428\",\"province_code\":\"90\",\"eparchy_code\":\"0432\",\"pay_fee\":2000,\"recharge_time\":\"2017-12-29T08:46:54.000+08:00\",\"opera_time\":\"2017-12-29T08:46:54.000+08:00\",\"kafka_type\":\"l\",\"request_time\":\"2017-12-29T08:46:54.000+08:00\",\"ret_code\":\"0\",\"ret_msg\":\"领券成功\",\"log_time\":\"2017-12-29T08:46:54.000+08:00\"}"
    println(JSON.parseObject(StringUtil.underlineToHump(new StringBuffer(str)).toString, classOf[TbIUser]))
  }
}
