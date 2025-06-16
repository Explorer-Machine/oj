package org.example.oj.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.pojo.template.judgeTemplate.AbstractJudgeTemplate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitMessageDTO {
    private Long submitId;

    private AbstractJudgeTemplate judgeTemplate;
}
