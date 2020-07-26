package com.a205.mybed.pictureservice.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    @LoadBalanced // 开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
