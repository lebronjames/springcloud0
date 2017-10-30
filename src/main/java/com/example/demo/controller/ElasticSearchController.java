package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/es")
@Api(value = "es", description = "ElasticSearch管理")
public class ElasticSearchController {
	private static final Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

	@GetMapping
    public String hello() {
        return "Hello Spring-Boot";
    }
}
