package com.bay.sparkspringboot.sparkdemo;

import com.bay.sparkspringboot.sparkdemo.util.IpParserUtil;

/**
 * Author by BayMin, Date on 2019/1/2.
 */
public class IpUtilsTest {
    public static void main(String[] args) {
        String ip = "60.246.36.222";
        System.out.println(IpParserUtil.parserIpByAPI(ip).toString());
        System.out.println(new IpParserUtil().parserIpByIpSeeker(ip).toString());
    }
}
