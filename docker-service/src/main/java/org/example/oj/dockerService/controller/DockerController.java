package org.example.oj.dockerService.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import org.example.oj.common.pojo.entity.CheckPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("docker")
public class DockerController {
    @Autowired
    private DockerClient dockerClient;

    @PostMapping("pull/{imageName}")
    public boolean pullImage(@PathVariable  String imageName) throws InterruptedException {
        return dockerClient.pullImageCmd(imageName).start().awaitStarted(30, TimeUnit.SECONDS);
    }

    @GetMapping("inspect/{imageName}")
    public String inspectImage(@PathVariable String imageName){
        return String.valueOf(dockerClient.inspectImageCmd(imageName).exec());
    }

    @DeleteMapping("delete/{imageName}")
    public void deleteImage(@PathVariable String imageName){
        dockerClient.removeImageCmd(imageName).exec();
    }

    public void createContainer(String imageName, String containerName){
        CreateContainerResponse response = dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withVolumes()
                .exec();
    }
}
