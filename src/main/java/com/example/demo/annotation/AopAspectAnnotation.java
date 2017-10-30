package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Aop Aspect切面类注解
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.annotation   
* 类名称:    AopAspectAnnotation.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月30日
 */
@Documented //表示是否将注解信息添加在java文档中
@Retention(RetentionPolicy.RUNTIME)//始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
@Target(ElementType.METHOD)//表示该注解用于方法
public @interface AopAspectAnnotation {

	String value() default "";
	
	String name() default "";
}
