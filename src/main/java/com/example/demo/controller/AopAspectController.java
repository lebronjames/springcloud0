package com.example.demo.controller;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.AopAspectAnnotation;

import io.swagger.annotations.Api;

/**
 * Aop Aspect测试
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.controller   
* 类名称:    AopAspectController.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月30日
 */
@RestController
@RequestMapping("/aopAspect")
@Api(value = "aopAspect", description = "Aop拦截器Aspect管理")
public class AopAspectController {
	
	private static final Logger logger = LoggerFactory.getLogger(AopAspectController.class);

	/**
	 * 原始注解方式
	 */
	@GetMapping("/original")
	public void originalAnnatation() {
		Class aopAspectController = AopAspectController.class;
		for(Method method : aopAspectController.getMethods()) {
			AopAspectAnnotation aopAspectAnnotation = method.getAnnotation(AopAspectAnnotation.class);//返回注解信息
			if(aopAspectAnnotation != null) {
				logger.info("---Method Name : {}",method.getName());
				logger.info("---Annotation Name : {}",aopAspectAnnotation.name());
				logger.info("---Annotation Value : {}",aopAspectAnnotation.value());
			}
		}
	}
	
	@GetMapping("/hello")
	public String hello() {
		logger.info("-----------AopAspectController##hello");
		return "hello,yzx";
	}
	
	@AopAspectAnnotation(value="annotationValue",name="annotationName")
	@GetMapping("/show")
	public void show() {
		logger.info("-----------AopAspectController##show");
	}
}
