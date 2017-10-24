package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.Channel;

import tk.mybatis.mapper.common.Mapper;

public interface ChannelMapper extends Mapper<Channel> {

	public Channel queryChannelById(Long id);
	
	public List<Channel> queryAllPdChannel();
	
	public void saveChannel(Channel channel);
	
	public void saveChannels(List<Channel> channels);
}
