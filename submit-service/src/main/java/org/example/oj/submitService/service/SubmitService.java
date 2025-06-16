package org.example.oj.submitService.service;

import org.example.oj.common.client.CheckPointClient;
import org.example.oj.common.pojo.dto.SubmitCreateDTO;
import org.example.oj.common.pojo.dto.SubmitMessageDTO;
import org.example.oj.common.pojo.enmu.judge.JudgeMethodEnum;
import org.example.oj.common.pojo.entity.Submit;
import org.example.oj.common.pojo.exception.UnsupportedJudgeMethodException;
import org.example.oj.common.pojo.template.judgeTemplate.AbstractJudgeTemplate;
import org.example.oj.common.pojo.template.judgeTemplate.IOJudgeTemplate;
import org.example.oj.common.pojo.template.judgeTemplate.SPJudgeTemplate;
import org.example.oj.common.util.SnowflakeGenerator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO 1.把submitCreateDTO变为Submit, 再创造一个submitMessageDTO, 先初次存入Submit
//TODO 2.创造一个RabbitmqTemplate，把评测通过消息队列发出去

@Service
public class SubmitService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    private CheckPointClient checkPointClient;

    public void submit(SubmitCreateDTO submitCreateDTO){
        Submit submit = submitCreateDTOConvertToSubmit(submitCreateDTO);
        AbstractJudgeTemplate judgeTemplate;
        if(submit.getJudgeMethod() == JudgeMethodEnum.IO)
            judgeTemplate = new IOJudgeTemplate();
        else if(submit.getJudgeMethod() == JudgeMethodEnum.SPJ)
            judgeTemplate = new SPJudgeTemplate();
        else
            throw new UnsupportedJudgeMethodException();
        judgeTemplate.setCode(submit.getCode());
        judgeTemplate.setArgs(submit.getArgs());
        judgeTemplate.setLanguage(submit.getLanguage());
        judgeTemplate.setCheckPointList(checkPointClient.getCheckPointList(submit.getProblemId()));
        judgeTemplate.setWorkingDir(String.format("/working/%l/%l/%l", submit.getUserId(), submit.getProblemId(), submit.getSubmitId()));

        SubmitMessageDTO submitMessageDTO = new SubmitMessageDTO(submit.getSubmitId(), judgeTemplate);
        rabbitTemplate.convertAndSend("oj.judge.judge", submitMessageDTO);
    }

    public static Submit submitCreateDTOConvertToSubmit(SubmitCreateDTO submitCreateDTO){
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator(1, 1);
        return new Submit(snowflakeGenerator.nextId(), submitCreateDTO.getUserId(), submitCreateDTO.getProblemId(), submitCreateDTO.getCode(), submitCreateDTO.getArgs(), submitCreateDTO.getLanguageEnum(), submitCreateDTO.getJudgeMethod(), submitCreateDTO.getSubmitDate(), submitCreateDTO.getSubmitTime());
    }
}
