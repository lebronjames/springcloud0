package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.AopAspectAnnotation;

/**
 * Aspect  测试类
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.aop   
* 类名称:    AspectAop.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月30日
 */
@Aspect //aop切面类注解
@Order(-99) //控制多个Aspect的执行顺序，越小越先执行
@Component
public class AspectAop {
	
	private static final Logger logger = LoggerFactory.getLogger(AspectAop.class);

	//拦截被AopAspectAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)
	@Before("@annotation(aaAnnotation)")
	public void beforeAop(JoinPoint joinPoint, AopAspectAnnotation aaAnnotation) throws Throwable {
		logger.info("-AspectAop##beforeAop##name:"+aaAnnotation.name()+",value:"+aaAnnotation.value());
	}
	
	@After("@annotation(aaAnnotation)")
	public void afterAop(JoinPoint joinPoint, AopAspectAnnotation aaAnnotation) throws Throwable {
		logger.info("-AspectAop##afterAop##name:"+aaAnnotation.name()+",value:"+aaAnnotation.value());
	}
}
