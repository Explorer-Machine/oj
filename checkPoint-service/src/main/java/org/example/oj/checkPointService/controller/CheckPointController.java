package org.example.oj.checkPointService.controller;

import org.example.oj.checkPointService.service.CheckPointService;
import org.example.oj.common.pojo.entity.CheckPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.util.List;

@RestController
@RequestMapping("checkPoint")
public class CheckPointController {
    @Autowired
    CheckPointService checkPointService;

    @GetMapping("/{problemId}")
    public List<CheckPoint> getCheckPointList(@PathVariable Long problemId){
        //TODO 暂时返回null
        return null;
    }
}
