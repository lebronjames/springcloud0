package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.config.TestConfiguration;
import com.example.demo.config.TestConfigurationBean;

@SpringBootApplication
//@ServletComponentScan //使用@ServletComponentScan 注解后，Servlet、Filter、Listener 
//可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
@MapperScan("com.example.demo.mapper")
//添加自动扫描注解，basePackages为包路径。初始化@Component的bean
@ComponentScan(basePackages = "com.example.demo")
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
public class SpringCloud0Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloud0Application.class, args);
//		initConfig();
	}
	
	public static void initConfig() {
		// @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
		
		//获取Bean
		TestConfigurationBean bean = new TestConfigurationBean();
		bean.say();
	}
}
