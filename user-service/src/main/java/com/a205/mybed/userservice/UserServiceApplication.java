package com.a205.mybed.userservice;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    /**
     * test
     */
    @Autowired
    RestTemplate template;

    @Bean
    @LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    /**
     * 调用其他微服务演示
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "this is user-service\n and call picture-service : " +
                template.getForObject("http://picture-service/test", String.class);
    }

    /**
     * 引入其他模块类测试
     */
    TestUtil util=new TestUtil();


}
