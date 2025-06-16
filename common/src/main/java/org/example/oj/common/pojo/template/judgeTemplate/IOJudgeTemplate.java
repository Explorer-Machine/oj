package org.example.oj.common.pojo.template.judgeTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oj.common.client.DockerServiceClient;
import org.example.oj.common.pojo.enmu.judge.LanguageEnum;
import org.example.oj.common.pojo.entity.CheckPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IOJudgeTemplate extends AbstractJudgeTemplate {
    @Autowired
    DockerServiceClient dockerServiceClient;

    @Override
    public void judge() {
        dockerServiceClient.createContainer("cpp", 1, 256, "cout << hello << endl", "-o2", String.valueOf(LanguageEnum.Cpp), null);
    }
}
