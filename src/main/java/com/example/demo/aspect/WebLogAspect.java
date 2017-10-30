package com.example.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Web 请求日志AOP
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.aspect   
* 类名称:    WebLogAspect.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月30日
 */
@Aspect
@Component
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	
	//使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数
	//两个..代表所有子目录，最后括号里的两个..代表所有参数
	@Pointcut("execution(public * com.example.demo..controller.*.*(..))")
	public void logPointCut() {
	}
	
	@Before("logPointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        // 记录下请求内容
        logger.info("请求地址 :"+request.getRequestURI().toString());
        logger.info("HTTP METHOD :"+request.getMethod());
        logger.info("IP :"+request.getRemoteAddr());
        logger.info("CLASS_METHOD :"+joinPoint.getSignature().getDeclaringTypeName()
        			+"."+joinPoint.getSignature().getName());
        logger.info("参数 :"+Arrays.toString(joinPoint.getArgs()));
	}
	
	// returning的值和doAfterReturning的参数名一致
	@AfterReturning(returning="ret", pointcut="logPointCut()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("返回值 :"+ret);
	}
	
	@Around("logPointCut()")
	public Object doAroud(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object object = pjp.proceed();// object 为方法的返回值
		logger.info("方法请求耗时 : " + (System.currentTimeMillis() - startTime));
		return object;
	}
	
}