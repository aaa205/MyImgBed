package com.a205.mybed.pictureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class PictureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureServiceApplication.class, args);
    }

    /**
     * test
     */
    @GetMapping("test")
    public String test(){
        return "this is picture-service";
    }

}
