package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Channel;
import com.example.demo.mapper.ChannelMapper;

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
	
	public void saveChannel(Channel channel) {
		channel.setCreateTime(new Date());
		channel.setUpdateTime(new Date());
		channel.setIsWholeChannel((byte) 0);
		channel.setChannelCode("" + System.currentTimeMillis());
		channelMapper.saveChannel(channel);
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
}
