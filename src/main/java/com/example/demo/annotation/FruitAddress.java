package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果供应者注解
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.annotation   
* 类名称:    FruitAddress.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月30日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitAddress {

	/**
	 * 供应商编号
	 * @return
	 */
	public int id() default -1;
	//供应商名称
	public String name() default "";
	//供应商地址
	public String address() default "";
}
