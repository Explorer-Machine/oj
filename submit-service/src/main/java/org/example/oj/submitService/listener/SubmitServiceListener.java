package org.example.oj.submitService.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class SubmitServiceListener {
    @RabbitListener()
    //这里写一个listener用于接听docker执行结果
}
