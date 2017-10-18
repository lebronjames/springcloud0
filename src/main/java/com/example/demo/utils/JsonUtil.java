package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		//有一个属性passwordXXXX,但User类的属性没有对应的passwordXXXX属性,如果使用这个字符串反序列化为User对象就会抛JsonMappingException异常.
		//但是如果将DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES设为false,那么就会忽略passwordXXXX这个属性的解析
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * 
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student[].class
	 * 然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * json数组转List
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 将javabean转成json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	  /**
	   * 将Map转成对应的JavaBean
	   * 
	   * @param map
	   * @param clazz
	   * @return
	   */
	  public static <T> T mapToBean(Map<String, Object> map,Class<T> clazz) {
		  try {
		      String jsonStr = objectMapper.writeValueAsString(map);
		      return objectMapper.readValue(jsonStr, clazz);
		    } catch (Exception e) {
		      LOGGER.error(e.getMessage());
		    }
		    return null;
	  }
	  
	  /**
	   * 将Bean转换成Map
	   * 
	   * @param object
	   * @return
	   */
	  public static Map<String, Object> beanToMap(Object object){
		  try {
				String jsonStr = objectMapper.writeValueAsString(object);
				return objectMapper.readValue(jsonStr, HashMap.class);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		  	return null;
	  }
}
