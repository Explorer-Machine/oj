package org.example.oj.problemService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @GetMapping("/problemList")
    public String getProblemList(){
        return "some problem list";
    }
}
