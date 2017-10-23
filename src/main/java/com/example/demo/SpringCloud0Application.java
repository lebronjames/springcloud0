package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ServletComponentScan //使用@ServletComponentScan 注解后，Servlet、Filter、Listener 
//可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
public class SpringCloud0Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloud0Application.class, args);
	}
}
