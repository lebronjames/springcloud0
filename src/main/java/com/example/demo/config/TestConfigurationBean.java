package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//添加注册bean的注解
@Component
public class TestConfigurationBean {

	private static final Logger logger = LoggerFactory.getLogger(TestConfigurationBean.class);
	
	private String username;
	private String url;
	private String password;
	
	public void say() {
		logger.info("TestBean say()......");
	}
	
	public void start() {
		logger.info("TestBean 初始化。。。");
    }
	
	public void cleanUp() {
		logger.info("TestBean 销毁。。。");
    }
	
	public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
