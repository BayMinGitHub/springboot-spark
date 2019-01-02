package com.bay.sparkspringboot.sparkdemo.util;


import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author by BayMin, Date on 2018/11/14.
 */
@Slf4j
public class StringUtil {
    private static Pattern underlineToHumpPattern = Pattern.compile("_(\\w)");
    private static Pattern humpToUnderlinePattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    public static StringBuffer underlineToHump(StringBuffer str) {
        Matcher matcher = underlineToHumpPattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return underlineToHump(sb);
    }

    /**
     * 驼峰转下划线
     */
    public static StringBuffer humpToUnderline(StringBuffer str) {
        Matcher matcher = humpToUnderlinePattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return humpToUnderline(sb);
    }
}
