package com.example.demo.controller;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Channel;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/redis")
@Api(value = "redis", description = "Redis管理")
public class RedisController {
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate redisTemplate;
    
	@GetMapping
    public String hello() throws InterruptedException {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Channel channel = new Channel();
		ValueOperations<String, Channel> operations=redisTemplate.opsForValue();
        operations.set("com.neox", channel);
        operations.set("com.neo.f", channel,1,TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        return "Hello Spring-Boot";
    }
}
