package com.bay.sparkspringboot.sparkdemo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Author by BayMin, Date on 2019/1/2.
 */
public class StringTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = "L 12/29/2018 - 11:55:26: \"名字系统爆炸<885><STEAM_1:1:156901449><>\" connected, address \"183.36.69.215:10557\"";
        System.out.println(data.substring(data.indexOf(": \"") + 3, data.indexOf(">", data.indexOf("STEAM")) + 1));
        System.out.println(Arrays.toString(data.replaceAll("[\"<>-]", " ").split("\\s+")));
        System.out.println(simpleDateFormat.format(new Date("12/29/2018" + " " + "23:55:26")));
    }
}
