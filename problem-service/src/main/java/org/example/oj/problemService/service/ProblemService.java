package org.example.oj.problemService.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService {

    public List<String> getProblemTitleList(){
        ArrayList<String> problemTitleList = new ArrayList<>();
        problemTitleList.add("Problem #1");
        problemTitleList.add("Problem #2");
        return problemTitleList;
    }
}
