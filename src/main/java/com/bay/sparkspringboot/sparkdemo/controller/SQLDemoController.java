package com.bay.sparkspringboot.sparkdemo.controller;

import com.bay.sparkspringboot.sparkdemo.model.TbIUser;
import com.bay.sparkspringboot.sparkdemo.service.SQLDemoService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * Author by BayMin, Date on 2018/12/7.
 */
@Slf4j
@RestController
@RequestMapping("/spark")
public class SQLDemoController {
    @Resource
    private SQLDemoService sqlDemoService;

    @RequestMapping("/sql_demo")
    @ResponseBody
    public LinkedList<TbIUser> sqlDemo(@NonNull @RequestParam("tableName") String tableName) {
        return sqlDemoService.run(tableName);
    }
}
