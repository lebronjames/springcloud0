package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.demo.entity.Channel;

import tk.mybatis.mapper.common.Mapper;

//@Mapper
@CacheConfig(cacheNames="channel")
public interface ChannelMapper extends Mapper<Channel> {
//public interface ChannelMapper extends tk.mybatis.mapper.common.Mapper<Channel> {
	
	public void saveChannel(Channel channel);
	
	public void saveChannels(List<Channel> channels);
	
	//如果指定为 true，则方法调用后将立即清空所有缓存
	@CacheEvict(key="#p0",allEntries=true)//指定key，删除缓存数据，allEntries=true,方法调用后将立即清除缓存
	public void deleteById(@Param("id")Long id);
	
	public void updateChannel(Channel channel);
	
	@CachePut(key="#p0")//指定key，将更新的结果同步到redis中
	public void updateChannelName(@Param("id")Long id, @Param("channelName")String channelName);
	
	@Cacheable(key="#p0")//将查询结果缓存到redis中，（key="#p0"）指定传入的第一个参数作为redis的key
	public Channel queryChannelById(@Param("id")Long id);
	
	@Cacheable(key="#p0")//将查询结果缓存到redis中，（key="#p0"）指定传入的第一个参数作为redis的key
	public Channel queryChannelByCode(@Param("channelCode")String channelCode);
	
	public List<Channel> queryAllPdChannel();
	
}
