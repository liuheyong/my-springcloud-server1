package com.lhy.cloud.server1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//启用feign进行远程调用
@EnableFeignClients
//添加熔断
@EnableCircuitBreaker
@MapperScan("com.lhy.cloud.server1.mapper")
//启用注册中心
@EnableDiscoveryClient
@SpringBootApplication
public class Server1Application {

    public static void main(String[] args) {
        SpringApplication.run(Server1Application.class, args);
    }

}