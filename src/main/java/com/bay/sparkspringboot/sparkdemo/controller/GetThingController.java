package com.bay.sparkspringboot.sparkdemo.controller;

import com.bay.sparkspringboot.sparkdemo.service.GetThingService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Author by BayMin, Date on 2018/12/29.
 */
@Slf4j
@RestController
@RequestMapping("/spark")
public class GetThingController {
    @Resource
    private GetThingService GetThingService;

    @RequestMapping("/get_say")
    @ResponseBody
    public Map<String, String> getSay(@NonNull @RequestParam("filePath") String filePath) {
        return GetThingService.getSay(filePath);
    }

    @RequestMapping("/get_ip")
    @ResponseBody
    public Map<String, String> getIp(@NonNull @RequestParam("filePath") String filePath) {
        return GetThingService.getIp(filePath);
    }
}
