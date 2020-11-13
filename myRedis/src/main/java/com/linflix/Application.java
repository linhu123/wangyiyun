package com.linflix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping
@RestController
public class Application {

    @Autowired
    private RedisTemplate redisTemplate;
//    public Application(RedisTemplate redisTemplate) {
//        redisTemplate.opsForValue().set("spring-r-cluster-1", 123);
//        redisTemplate.opsForValue().set("spring-r-cluster-2", 456);
//        redisTemplate.opsForValue().set("spring-r-cluster-3", 789);
//    }
    @RequestMapping(value = "/init")
    public String init(){
        double random = Math.random();
        redisTemplate.opsForValue().set(String.valueOf(random), random);
        return String.valueOf(random);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
