package org.example.oj.common.client;

import org.example.oj.common.pojo.entity.CheckPoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient
public interface DockerServiceClient {

    @PostMapping("docker/start")
    public void startContainer(@RequestParam String containerId);

    @PostMapping("docker/create")
    public void createContainer(String image, int cpuShares, long memoryMB, String code, String args, String language, List<CheckPoint> checkPointList);
}
