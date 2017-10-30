package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果名称注解
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.annotation   
* 类名称:    FruitName.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月30日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {

	String value() default "";
}
