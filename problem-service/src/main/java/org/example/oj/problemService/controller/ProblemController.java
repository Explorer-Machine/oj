package org.example.oj.problemService.controller;

import org.example.oj.problemService.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @GetMapping("/problemList")
    public List<String> getProblemList(){
        return problemService.getProblemTitleList();
    }
}
