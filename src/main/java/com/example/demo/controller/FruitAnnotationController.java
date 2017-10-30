package com.example.demo.controller;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.FruitAddress;
import com.example.demo.annotation.FruitColor;
import com.example.demo.annotation.FruitName;
import com.example.demo.model.AppleModel;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/fruit")
@Api(value = "fruit", description = "fruitAnnotation管理")
public class FruitAnnotationController {

	private static final Logger logger = LoggerFactory.getLogger(FruitAnnotationController.class);
	
	@GetMapping("/info")
	public void info() {
		String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";
        
        Field[] fields = AppleModel.class.getDeclaredFields();
        
        for(Field field : fields) {
        	if(field.isAnnotationPresent(FruitName.class)) {//判断指定类型的注释是否存在于此元素上
        		FruitName fruitName = (FruitName)field.getAnnotation(FruitName.class); 
        		strFruitName = strFruitName + fruitName.value();
        		logger.info(strFruitName);
        	}else if(field.isAnnotationPresent(FruitColor.class)) {
        		FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
        		strFruitColor += fruitColor.fruitColor().toString();
        		logger.info(strFruitColor);
        	}else if(field.isAnnotationPresent(FruitAddress.class)) {
        		FruitAddress fruitAddress = (FruitAddress) field.getAnnotation(FruitAddress.class);
        		strFruitProvicer = "供应商编号："+fruitAddress.id()+" 供应商名称："+fruitAddress.name()
        			+" 供应商地址："+fruitAddress.address();
        		logger.info(strFruitProvicer);
        	}
        }
	}
	
}
