package com.a205.mybed.pictureservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.a205.mybed.pictureservice.dao")
public class PictureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureServiceApplication.class, args);
    }


}
