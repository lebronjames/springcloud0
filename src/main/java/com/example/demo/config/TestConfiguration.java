package com.example.demo.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Configuation使用示例
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.config   
* 类名称:    TestConfiguration.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月24日
 */
@Configuration
public class TestConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(TestConfiguration.class);
	
	public TestConfiguration() {
		logger.info("------------------------TestConfiguration容器启动初始化。。。，时间：{}",new Date());
	}
	
	// @Bean注解注册bean,同时可以指定初始化和销毁方法
	// @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
	//使用@ComponentScan自动扫描注解，@Component注册的Bean
	/*@Bean
	@Scope("prototype")
	public TestConfigurationBean testBean() {
		return new TestConfigurationBean();
	}*/
}
