package org.example.oj.common.pojo.template.judgeTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.pojo.enmu.judge.JudgeMethodEnum;
import org.example.oj.common.pojo.enmu.judge.LanguageEnum;
import org.example.oj.common.pojo.entity.CheckPoint;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractJudgeTemplate {
    protected String code;
    protected LanguageEnum language;
    protected String args;
    protected List<CheckPoint> checkPointList;

    protected String workingDir;
    public abstract void judge();
}
