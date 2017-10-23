package com.example.demo.cache;

import org.springframework.stereotype.Component;

/**
 * redis缓存操作组件
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.cache   
* 类名称:    RedisCacheComponent.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月18日
 */
@Component("redisCacheComponent")
public class RedisCacheComponent  {

//	@Resource(name = "redisClusterTemplate")
//	  private JedisCluster redisClusterTemplate;
//	 
//	  private final Logger logger = LoggerFactory.getLogger(RedisCacheComponent.class);
//	  
//	  /**
//	   * 获取单个值
//	   * 
//	   * @param key
//	   *          缓存键
//	   * @param value
//	   *          缓存的字段对应的值
//	   */
//	  @Override
//	  public Object get(String key) {
//		  Object result = null;
//		  try{
//	          result = JsonSerializeUtil.unserialize(redisClusterTemplate.get(JsonSerializeUtil.serializeKey(key)));
//		  }catch(Exception e){
//			  logger.error("redis get error, key:"+key, e);
//		  }
//		  return result;
//	  }
//
//	  /**
//	   * 缓存单个值
//	   * @param key
//	   *         缓存键
//	   * @param value
//	   *         缓存键对应的值
//	   */
//	  public void set(String key,Object value){
//		  try{
//			  redisClusterTemplate.set(JsonSerializeUtil.serializeKey(key),JsonSerializeUtil.serialize(value));
//		  }catch(Exception e){
//			  logger.error("redis set error, key:"+key, e);
//		  }
//	  }
//	  
//	  /**
//	   * 缓存Map中的单个值
//	   * 
//	   * @param key
//	   *          缓存键
//	   * @param field
//	   *          缓存中的Map的字段
//	   * @param value
//	   *          缓存的字段对应的值
//	   */
//	  @Override
//	  public void hput(String key, String field, Object value) {
//	      try{
//			  // 序列化缓存map中的单个值
//			  redisClusterTemplate.hset(JsonSerializeUtil.serializeKey(key),
//		      JsonSerializeUtil.serializeKey(field),
//		      JsonSerializeUtil.serialize(value));
//	      }catch(Exception e){
//	    	  StringBuilder sb = new StringBuilder();
//	    	  sb.append("redis hset error, mapkey:").append(key).append(",fieldKey:").append(field);
//	    	  logger.error(sb.toString(), e);
//	      }
//	  }
//
//	  /**
//	   * 获取Map中的单个值
//	   * 
//	   * @param key
//	   *          缓存键
//	   * @param field
//	   *          缓存中的Map的字段
//	   * @return 缓存的字段对应的值
//	   */
//	  @Override
//	  public Object hget(String key, String field) {
//		Object result = null;
//		try{
//			result = JsonSerializeUtil.unserialize(redisClusterTemplate
//		        .hget(JsonSerializeUtil.serializeKey(key), JsonSerializeUtil.serializeKey(field)));
//		}catch(Exception e){
//			StringBuilder sb = new StringBuilder();
//	  	  	sb.append("redis hget error, mapkey:").append(key).append(",fieldKey:").append(field);
//	  	  	logger.error(sb.toString(), e);
//		}
//		return result;
//	  }
//
//	  /**
//	   * 移除Map中的单个值
//	   * 
//	   * @param key
//	   *          缓存键
//	   * @param field
//	   *          缓存中的Map的字段
//	   * @return 缓存的字段对应的值
//	   */
//	  @Override
//	  public void remove(String key, String field) {
//		  try{
//			  redisClusterTemplate.hdel(JsonSerializeUtil.serializeKey(key), JsonSerializeUtil.serializeKey(field));
//		  }catch(Exception e){
//			  StringBuilder sb = new StringBuilder();
//		  	  sb.append("redis hdel error, mapkey:").append(key).append(",fieldKey:").append(field);
//			  logger.error(sb.toString(),e);
//		  }
//	  }
//
//	  /**
//	   * 判断key是否存在
//	   * 
//	   * @param key
//	   *          缓存键
//	   * @return key是否存在
//	   */
//	  @Override
//	  public boolean exists(String key) {
//		  Boolean flag = null;
//		  try{
//			  flag = redisClusterTemplate.exists(JsonSerializeUtil.serializeKey(key));
//		  }catch(Exception e){
//			  logger.error("redis exists error, key:"+key,e);
//		  }
//		  return flag;
//	  }
//
//	  /**
//	   * 设置对应key的失效时间
//	   * 
//	   * @param key
//	   * @param seconds
//	   */
//	  @Override
//	  public void setExpireTime(String key, int seconds) {
//		  try{
//			  redisClusterTemplate.expire(key, seconds);
//		  }catch(Exception e){
//			  logger.error("redis expire error, key:"+key, e);
//		  }
//	  }
//
//	  
//	  /**
//	   * 分布式锁
//	   * @param key
//	   * @param value
//	   * @return
//	   */
//	  @Override
//	  public long setnx(String key,Object value){
//		  Long lock = null;
//		  try{
//			  lock = redisClusterTemplate.setnx(JsonSerializeUtil.serializeKey(key), JsonSerializeUtil.serialize(value));
//		  }catch(Exception e){
//			  logger.error("redis setnx error,key:"+key,e);
//		  }
//		  return lock;
//	  }
//	  
//	  /**
//	   * 移除key
//	   * @param key
//	   */
//	  @Override
//	  public void remove(String key){
//		 try{
//			 redisClusterTemplate.del(key);
//		 }catch(Exception e){
//			 logger.error("redis del error,key:"+key, e);
//		 }
//	  }
}
