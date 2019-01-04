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

    @RequestMapping("/get_thing")
    @ResponseBody
    public Map<String, String> getThing(@NonNull @RequestParam("method") String method, @NonNull @RequestParam("filePath") String filePath) {
        switch (method) {
            case "getSay":
                return GetThingService.getSay(filePath);
            case "getIp":
                return GetThingService.getIp(filePath);
            default:
                return null;
        }
    }
}
