/**
 * 
 */
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Channel;
import com.example.demo.service.ChannelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**   
 * Redis 单机集成
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.controller   
* 类名称:    SingleRedisController.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月26日   
*/
@RestController
@RequestMapping("/redis/single")
@Api(value = "singleRedis", description = "Redis单机管理")
public class SingleRedisController {

private static final Logger logger = LoggerFactory.getLogger(SingleRedisController.class);
	
	@Autowired
	private ChannelService channelService;
	
	/**
	 * redis缓存维护-保存
	 * @param channel
	 */
	@PostMapping("/channel/save")
	@ApiOperation(value="redis单机保存", notes="redis单机保存")
	public void saveChannel(@RequestBody Channel channel) {
		try {
			channelService.saveChannel(channel);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * redis缓存维护-删除
	 * @param id
	 */
	@DeleteMapping("/channel/delete/{id}")
	@ApiOperation(value="redis单机删除", notes="redis单机删除")
	public void deleteById(@PathVariable("id") String id) {
		try {
			channelService.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * redis缓存维护-修改
	 * @param id
	 * @param channelName
	 */
	@GetMapping("/channel/update/{id}")
	@ApiOperation(value="redis单机修改", notes="redis单机修改")
	public void updateChannelName(@PathVariable("id") String id, @RequestParam("channelName") String channelName) {
		try {
			channelService.updateChannelName(id, channelName);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * redis缓存维护-根据ID查询
	 * @param id
	 * @return
	 */
	@GetMapping("/channel/get/{id}")
	@ApiOperation(value="redis单机查询ID", notes="redis单机查询ID")
	public Channel getChannelBean(@PathVariable("id") String id) {
		return channelService.queryChannelById(id);
	}
	
	
	/**
	 * redis缓存维护-根据Code查询
	 * @param channelCode
	 * @return
	 */
	@GetMapping("/channel/getByCode/{channelCode}")
	@ApiOperation(value="redis单机查询Code", notes="redis单机查询Code")
	public Channel queryChannelByCode(@PathVariable("channelCode") String channelCode) {
		return channelService.queryChannelByCode(channelCode);
	}
}
