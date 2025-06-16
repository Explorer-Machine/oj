package org.example.oj.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.pojo.enmu.checkPoint.ResultStatusEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Long submitId;
    private Long resultId;
    private Long checkPointId;

    private Integer timeUsed;
    private Integer memoryUsed;
    private Integer fileSizeUsed;

    private Integer score;
    private ResultStatusEnum resultStatus;
}
