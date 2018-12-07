package com.bay.sparkspringboot.sparkdemo.controller;

import com.bay.sparkspringboot.sparkdemo.service.WordCountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import scala.collection.JavaConversions;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Author by BayMin, Date on 2018/12/7.
 */
@RestController
@RequestMapping("/spark")
public class WordCountController {
    @Resource
    private WordCountService wordCountService;

    @RequestMapping("/wordCount")
    @ResponseBody
    public Map<String, Integer> wordCount() {
        Map<String, Integer> map = null;
        try {
            map = wordCountService.run();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        }
        return map;
    }
}
