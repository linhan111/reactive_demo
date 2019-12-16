package com.linhan111.reactive_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Random;

/**
 * @author linhan111
 */
@RestController
@Slf4j
@RequestMapping("/public/api")
public class HelloController {

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @GetMapping("/hello")
    public Mono<String> hello() throws InterruptedException {
        // 【改】返回类型为Mono<String>
        // 【改】使用Mono.just生成响应式数据
        Thread.sleep(new Random().nextInt(10));
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping("/world")
    public String world() throws InterruptedException {
        Thread.sleep(new Random().nextInt(200));
        return "Welcome to the world ~";
    }

    @GetMapping("/testReactiveRedis")
    public void setRedisValue() {
        Mono mono1 = reactiveRedisTemplate.opsForValue().set(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5));
        mono1.subscribe(System.out::println);
    }
}
