package com.example.demo.controller;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.SpringCloud0Application;
import com.example.demo.entity.Channel;
import com.example.demo.service.ChannelService;
import com.example.demo.utils.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringBootTest(classes = SpringCloud0Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class TkMybatisTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TkMybatisTest.class);

	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private ChannelService channelService;

    MockMvc mvc;
    
    @Before
    public void setUp() {
    	//MockMvcBuilders使用构建MockMvc对象
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void testSaveChannel() throws Exception {
    	Channel channel = new Channel();
    	channel.setChannelName("test000");
    	channel.setChannelDesc("test000");
    	channel.setCreateTime(new Date());
		channel.setUpdateTime(new Date());
		channel.setIsWholeChannel((byte) 0);
		channel.setChannelCode("" + System.currentTimeMillis());
    	String content = JsonUtil.toJsonString(channel);
    	MvcResult result = mvc
    			.perform(MockMvcRequestBuilders.post("/channel/saveChannel")
    					//.header(name, values) //设置请求报文头
    					.contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
    			.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    	int responseCode = result.getResponse().getStatus();
    	Assert.assertEquals(responseCode, 200);
    	String responseBody = result.getResponse().getContentAsString();
    	logger.info("-------TkMybatisTest##testSaveChannel(),body:{}",responseBody);
    }
    
    @Test
    public void testGetChannel() throws Exception {
    	MvcResult result = mvc
    			.perform(MockMvcRequestBuilders.get("/tk/tkListByCondition")
    					.contentType(MediaType.APPLICATION_JSON_UTF8)
    					.param("fieldName", "channelName").param("fieldValue", "test"))
    			.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    	int responseCode = result.getResponse().getStatus();
    	Assert.assertEquals(responseCode, 200);
    	String responseBody = result.getResponse().getContentAsString();
    	logger.info("-------TkMybatisTest##testGetChannel(),body:{}",responseBody);
    }
}