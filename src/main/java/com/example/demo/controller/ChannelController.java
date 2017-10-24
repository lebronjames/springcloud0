package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Channel;
import com.example.demo.service.ChannelService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/channel")
@Api(value = "channel", description = "渠道管理")
public class ChannelController {

	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	
	@Autowired
	private ChannelService channelService;
	
	@GetMapping("/list")
	public List<Channel> list(){
		return channelService.list();
	}
	
	@GetMapping("/getChannelById")
	public Channel getChannelById(@RequestParam String id) {
		return channelService.queryChannelById(id);
	}
	
	@PostMapping("/saveChannel")
	public void saveChannel(@RequestBody Channel channel) {
		try {
			channelService.saveChannel(channel);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@GetMapping("/saveChannelTransaction")
	public void saveChannelTransaction(@RequestParam String channelName,@RequestParam String channelDesc) {
		try {
			channelService.saveChannelTransaction(channelName,channelDesc);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@PostMapping("/batchSave")
	public void batchSave(@RequestBody Channel[] channels) {
		try {
			channelService.batchSave(Arrays.asList(channels));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
}
