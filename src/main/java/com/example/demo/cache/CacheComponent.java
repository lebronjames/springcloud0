package com.example.demo.cache;

/**
 * 通用缓存操作接口
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.cache   
* 类名称:    CacheComponent.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月18日
 */
public interface CacheComponent {

	/**
	 * 缓存单个值
	 * @param key 缓存键
	 */
	public Object get(String key);

	/**
	 * 缓存Map中的单个值
	 * @param key 缓存键
	 * @param field 缓存中的Map的字段
	 * @param value 缓存的字段对应的值
	 */
	public void hput(String key, String field,Object value);
	
	/**
	 * 获取Map中的单个值
	 * @param key 缓存键
	 * @param field 缓存中的Map的字段
	 * @return 缓存的字段对应的值
	 */
	public Object hget(String key, String field);
	
	/**
	 * 移除Map中的单个值
	 * @param key 缓存键
	 * @param field 缓存中的Map的字段
	 * @return 缓存的字段对应的值
	 */
	public void remove(String key, String field);
	
	/**
	 * 判断key是否存在
	 * @param key 缓存键
	 * @return key是否存在
	 */
	public boolean exists(String key);
	
	/**
	 * 设置对应key的失效时间
	 * @param key
	 * @param seconds
	 */
	public void setExpireTime(String key, int seconds);
	
	/**
	 * 分布式锁
	 * @param key
	 * @param value
	 * @return
	 */
	public long setnx(String key,Object value);
	
	/**
	 * 移除key
	 * @param key
	 */
	public void remove(String key);
}
