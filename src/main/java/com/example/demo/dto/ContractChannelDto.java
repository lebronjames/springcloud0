package com.example.demo.dto;

public class ContractChannelDto {

	private Long channelId;
	private String name;
	private String channelDesc;
	private String isAllChannel;
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChannelDesc() {
		return channelDesc;
	}
	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}
	public String getIsAllChannel() {
		return isAllChannel;
	}
	public void setIsAllChannel(String isAllChannel) {
		this.isAllChannel = isAllChannel;
	}
}
