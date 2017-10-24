package com.example.demo.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContractChannelDto;
import com.example.demo.utils.JsonUtil;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/jdbc")
@Api(value = "jdbc", description = "SpringJDBC管理")
public class JdbcController {

	private static final Logger logger = LoggerFactory.getLogger(JdbcController.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private JdbcService jdbcService;
	
	@GetMapping("/list")
	public String getChannelList() {
		String sql = "select id,channel_name,channel_desc,is_whole_channel from pd_channel";
		
		List<ContractChannelDto> list = (List<ContractChannelDto>)jdbcTemplate.query(sql, new RowMapper<ContractChannelDto>() {

			@Override
			public ContractChannelDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContractChannelDto dto = new ContractChannelDto();
				dto.setChannelId(rs.getLong("id"));
				dto.setName(rs.getString("channel_name"));
				dto.setChannelDesc(rs.getString("channel_desc"));
				dto.setIsAllChannel(rs.getString("is_whole_channel"));
				return dto;
			}
		});
		return JsonUtil.toJsonString(list);
	}
	
	@GetMapping("/channels")
	public List<ContractChannelDto> getChannels() {
		String sql = "select channel_id,name,channel_desc,is_all_channel from t_contract_channel";
		
		return (List<ContractChannelDto>)jdbcTemplate.query(sql, new RowMapper<ContractChannelDto>() {

			@Override
			public ContractChannelDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContractChannelDto dto = new ContractChannelDto();
				dto.setChannelId(rs.getLong("id"));
				dto.setName(rs.getString("name"));
				dto.setChannelDesc(rs.getString("channel_desc"));
				dto.setIsAllChannel(rs.getString("is_all_channel"));
				return dto;
			}
		});
	}
}
