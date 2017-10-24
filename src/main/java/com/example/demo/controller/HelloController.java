package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.TestConfigurationProperties;
import com.example.demo.dto.DemoDto;
import com.example.demo.model.HelloRequestModel;
import com.example.demo.model.HelloResponseModel;
import com.example.demo.service.HelloService;
import com.example.demo.utils.JsonUtil;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/hello")
@Api(value = "hello", description = "Hello管理")
public class HelloController {

	@Autowired
	private HelloService helloService;
	
	@Autowired
	private TestConfigurationProperties testConfigurationProperties;
	
	@GetMapping
    public String hello() {
        return "Hello Spring-Boot";
    }
	
	@GetMapping("/config")
    public String config() {
        return "Hello,name:"+testConfigurationProperties.getName()+",tech:"+testConfigurationProperties.getTech();
    }
	
	@PostMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }
	
	@GetMapping("/list")
    public List<DemoDto> getList(HttpServletRequest request) {
        List<DemoDto> list = new ArrayList<>();
        request.getSession().setAttribute("name", "qwertyuiop");
        DemoDto dto = null;
        for (int i = 1; i <= 5; i++) {
        	dto = new DemoDto();
        	dto.setId(i);
        	dto.setName("Lebron james" + i);
        	dto.setCreateTime(new Date());
            list.add(dto);
        }
        return list;
    }
	
	@GetMapping("/listmap")
    public List<Map<String, String>> getListMap() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<>();
            map.put("name", "Shanhy-" + i);
            list.add(map);
        }
        return list;
    }
	
	@PostMapping("/listByIds")
	public String listByIds(@RequestBody HelloRequestModel request) {
		HelloResponseModel response = helloService.handleRequest(request);
		return JsonUtil.toJsonString(response);
	}
}
