package org.example.oj.dockerService.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfig {
    @Bean
    public DockerClient dockerClient(){
        DefaultDockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder().build();
        return DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
    }
}
