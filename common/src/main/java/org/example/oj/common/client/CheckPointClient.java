package org.example.oj.common.client;

import org.example.oj.common.pojo.entity.CheckPoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("checkPoint-service")
public interface CheckPointClient {

    @GetMapping("/checkPoint/{problemId}")
    List<CheckPoint> getCheckPointList(@PathVariable Long problemId);
}
