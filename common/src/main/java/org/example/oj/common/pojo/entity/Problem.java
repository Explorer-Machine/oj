package org.example.oj.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.pojo.enmu.problem.ProblemDifficultyEnum;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private Long problemId;
    private String title;
    private String content;

    private String inputExample;
    private String outputExample;

    private List<Long> checkPointIds;

    private ProblemDifficultyEnum problemDifficulty;
    private Integer numOfAccepted;
    private Integer numOfSubmitted;

    private Long creatorId;
}
