package org.example.oj.submitService.controller;

import org.example.oj.common.pojo.dto.SubmitCreateDTO;
import org.example.oj.common.pojo.entity.Submit;
import org.example.oj.submitService.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.example.oj.common.pojo.entity.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("submit")
public class SubmitController {
    @Autowired
    SubmitService submitService;

    public Response createSubmission(@RequestBody SubmitCreateDTO submitCreateDTO){
        //do something here
        submitService.submit(submitCreateDTO);
        return new Response(200, "创建评测成功", null);
    }
}
