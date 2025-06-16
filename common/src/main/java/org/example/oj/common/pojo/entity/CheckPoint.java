package org.example.oj.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckPoint {
    private Long checkPointId;
    private Long problemId;
    private Integer sequenceOfTheProblem;

    private Integer timeLimit;
    private Integer memoryLimit;

    private String input;
    private String output;
}
