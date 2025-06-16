package org.example.oj.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.pojo.enmu.judge.JudgeMethodEnum;
import org.example.oj.common.pojo.enmu.judge.LanguageEnum;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitCreateDTO {
    private Long userId;
    private Long problemId;

    private LanguageEnum languageEnum;
    private JudgeMethodEnum judgeMethod;
    private String code;
    private String args;

    private Date submitDate;
    private Time submitTime;
}
