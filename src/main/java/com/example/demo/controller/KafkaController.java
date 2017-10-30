package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/kafka")
@Api(value = "kafka", description = "Kafka管理")
public class KafkaController {
	private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

	@GetMapping
    public String hello() {
        return "Hello Spring-Boot";
    }
}
