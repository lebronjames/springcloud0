package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Channel;
import com.example.demo.service.ChannelService;

import io.swagger.annotations.Api;

/**
 * 通用Mybatis接口
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.controller   
* 类名称:    TkMybatisController.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月24日
 */
@RestController
@RequestMapping("/tk")
@Api(value = "tk", description = "通用Mapper插件管理")
public class TkMybatisController {
	
	private static final Logger logger = LoggerFactory.getLogger(TkMybatisController.class);
	
	@Autowired
	private ChannelService channelService;

	/**
	 * 通用接口：根据主键删除
	 * @param id
	 * @return
	 */
	@GetMapping("/tkDeleteByPrimaryKey")
	public int tkDeleteByPrimaryKey(@RequestParam String id) {
		return channelService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 通用接口：根据实体删除
	 * @param id
	 * @return
	 */
	@GetMapping("/tkDeleteBean")
	public int tkDeleteBean(@RequestParam String id) {
		return channelService.deleteBean(id);
	}
	
	/**
	 * 通用接口：根据条件删除
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@GetMapping("/tkDeleteByExample")
	public int tkDeleteByExample(@RequestParam String fieldName, @RequestParam String fieldValue) {
//		example.createCriteria().andEqualTo("channelCode", "1508828984530");
		return channelService.deleteByExample(fieldName, fieldValue);
	}
	
	/**
	 * 通用接口：新增
	 * @param channelName
	 * @param channelDesc
	 * @return
	 */
	@GetMapping("/tkInsert")
	public int tkInsert(@RequestParam String channelName,@RequestParam String channelDesc) {
		return channelService.insert(channelName,channelDesc);
	}
	
	/**
	 * 通用接口：根据主键查询实体是否存在
	 * @param id
	 * @return
	 */
	@GetMapping("/tkExistWithPrimaryKey")
	public boolean tkExistWithPrimaryKey(String id) {
		return channelService.existWithPrimaryKey(id);
	}
	
	/**
	 * 通用接口：根据条件筛选列表
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@GetMapping("/tkListByCondition")
	public List<Channel> tkListByCondition(@RequestParam String fieldName, @RequestParam String fieldValue) {
		//example.createCriteria().andLike("channelName", "自动化");
		return channelService.listByCondition(fieldName,fieldValue);
	}
}
