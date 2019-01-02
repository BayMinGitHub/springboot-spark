package com.bay.sparkspringboot.sparkdemo;

import java.util.Arrays;

/**
 * Author by BayMin, Date on 2019/1/2.
 */
public class StringTest {
    public static void main(String[] args) {
        String data = "L 12/29/2018 - 11:55:26: \"名字系统爆炸<885><STEAM_1:1:156901449><>\" connected, address \"183.36.69.215:10557\"";
        System.out.println(data.substring(data.indexOf(": \"") + 3, data.indexOf(">", data.indexOf("STEAM")) + 1));
        System.out.println(Arrays.toString(data.replaceAll("[\"<>-]", " ").split("\\s+")));
    }
}
