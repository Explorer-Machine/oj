package org.example.oj.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.pojo.enmu.judge.JudgeMethodEnum;
import org.example.oj.common.pojo.enmu.judge.LanguageEnum;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submit {
    private Long submitId;

    private Long userId;
    private Long problemId;

    private String code;
    private String args;
    private LanguageEnum language;
    private JudgeMethodEnum judgeMethod;

    private Date submitDate;
    private Time submitTime;

    private Integer averageTimeCost;
    private Integer averageMemoryCost;
    private Integer averageFileSizeCost;

    private List<Long> results;

    public Submit(Long submitId, Long userId, Long problemId, String code, String args, LanguageEnum language, JudgeMethodEnum judgeMethod, Date submitDate, Time submitTime){
        this.submitId = submitId;
        this.userId = userId;
        this.problemId = problemId;
        this.code = code;
        this.args = args;
        this.language = language;
        this.judgeMethod = judgeMethod;
        this.submitDate = submitDate;
        this.submitTime = submitTime;
    }
}
