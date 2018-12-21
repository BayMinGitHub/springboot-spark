package com.bay.sparkspringboot.sparkdemo.controller;

import com.bay.sparkspringboot.sparkdemo.service.WordCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Author by BayMin, Date on 2018/12/7.
 */
@Slf4j
@RestController
@RequestMapping("/spark")
public class WordCountController {
    @Resource
    private WordCountService wordCountService;

    @RequestMapping("/wordCount")
    @ResponseBody
    public Map<String, Integer> wordCount(@RequestParam("filePath") String filePath) {
        Map<String, Integer> map = null;
        if ("".equals(filePath) || filePath == null) {
            log.error("please input filePath");
            return null;
        }
        try {
            map = wordCountService.run(filePath);
        } catch (FileNotFoundException e) {
            log.error("filePath no found");
        }
        return map;
    }
}
