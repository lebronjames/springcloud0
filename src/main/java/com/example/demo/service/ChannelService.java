package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Channel;
import com.example.demo.mapper.ChannelMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

@Service
public class ChannelService {
	
	@Autowired
	private ChannelMapper channelMapper;

	public List<Channel> list(){
		return channelMapper.queryAllPdChannel();
	}
	
	public Channel queryChannelById(String id) {
		return channelMapper.queryChannelById(Long.valueOf(id));
	}
	
	public Channel queryChannelByCode(String channelCode) {
		return channelMapper.queryChannelByCode(channelCode);
	}
	
	public void deleteById(String id) {
		channelMapper.deleteById(Long.valueOf(id));
	}
	
	public void updateChannelName(String id, String channelName) {
		channelMapper.updateChannelName(Long.valueOf(id), channelName);
	}
	
	public void saveChannel(Channel channel) {
		channel.setCreateTime(new Date());
		channel.setUpdateTime(new Date());
		channel.setIsWholeChannel((byte) 0);
		channel.setChannelCode("" + System.currentTimeMillis());
		channelMapper.saveChannel(channel);
	}
	
	@Transactional
	public void saveChannelTransaction(String channelName,String channelDesc) {
		Channel channel = new Channel();
		channel.setChannelName(channelName);
		channel.setChannelDesc(channelDesc);
		channel.setCreateTime(new Date());
		channel.setUpdateTime(new Date());
		channel.setIsWholeChannel((byte) 0);
		channel.setChannelCode("" + System.currentTimeMillis());
		channelMapper.saveChannel(channel);
//		int i = 1/0;
	}
	 
	public void batchSave(List<Channel> channels) {
		List<Channel> list = new ArrayList<Channel>();
		byte isWholeChannel = 0;
		Date date = new Date();
		for(Channel channel : channels) {
			channel.setCreateTime(date);
			channel.setUpdateTime(date);
			channel.setIsWholeChannel(isWholeChannel);
			channel.setChannelCode(""+System.currentTimeMillis()+new Random().nextInt(100));
			list.add(channel);
		}
		channelMapper.saveChannels(list);
	}
	
	public PageInfo<Channel> getChannelPage(String pageNum,String pageSize) {
		int num = 1;
		int size = 10;
		//若没有分页信息，默认第一页，十条数据
		if(StringUtils.isNotEmpty(pageNum)) {
			num = Integer.valueOf(pageNum);
		}
		if(StringUtils.isNotEmpty(pageSize)) {
			size = Integer.valueOf(pageSize);
		}
		PageHelper.startPage(num, size);
		return new PageInfo<Channel>(channelMapper.queryAllPdChannel()); 
	}
	
	public int deleteByPrimaryKey(String id) {
		return channelMapper.deleteByPrimaryKey(Long.valueOf(id));
	}
	
	public int deleteBean(String id) {
		Channel channel = new Channel();
		channel.setId(Long.valueOf(id));
		return channelMapper.delete(channel);
	}
	
	public int deleteByExample(String fieldName, String fieldValue) {
		Example example = new Example(Channel.class);
		example.createCriteria().andEqualTo(fieldName, fieldValue);
		return channelMapper.deleteByExample(example);
	}
	
	public int insert(String channelName,String channelDesc) {
		Channel channel = new Channel();
		channel.setChannelName(channelName);
		channel.setChannelDesc(channelDesc);
		channel.setCreateTime(new Date());
		channel.setUpdateTime(new Date());
		channel.setIsWholeChannel((byte) 0);
		channel.setChannelCode("" + System.currentTimeMillis()+new Random().nextInt(100));
		return channelMapper.insert(channel);
	}
	
	public boolean existWithPrimaryKey(String id) {
		return channelMapper.existsWithPrimaryKey(Long.valueOf(id));
	}
	
	public List<Channel> listByCondition(String fieldName, String fieldValue) {
		//通用Example查询
		Example example = new Example(Channel.class);
		example.createCriteria().andLike(fieldName, "%"+fieldValue+"%");
		return channelMapper.selectByExample(example);
	}
}
