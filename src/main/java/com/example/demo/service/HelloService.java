package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.demo.constants.HelloConstant;
import com.example.demo.dto.DemoDto;
import com.example.demo.model.HelloRequestModel;
import com.example.demo.model.HelloResponseModel;
import com.example.demo.model.RequestHeader;
import com.example.demo.model.Result;

@Service
public class HelloService {

	public HelloResponseModel handleRequest(HelloRequestModel request) {
		HelloResponseModel response = new HelloResponseModel();
		Result result = generateResult("0");
		if(this.checkHeader(request.getHeader())) {
			response.setResult(result);
			List<String> list = request.getList();
			if(!CollectionUtils.isEmpty(list)) {
				List<DemoDto> dtos = new ArrayList<DemoDto>();
				DemoDto dto = new DemoDto();
				for(String s : list) {
					dto.setId(Integer.valueOf(s));
					dto.setName("DemoDto"+s);
					dto.setCreateTime(new Date());
					dtos.add(dto);
				}
				response.setList(dtos);
			}
		}
		return response;
	}
	
	/**
	 * 请求头参数验证
	 * @param header
	 * @return
	 */
	public boolean checkHeader(RequestHeader header) {
		boolean flag = true;
		if(StringUtils.isEmpty(header.getVersion())) {
			flag = false;
		}
		if(StringUtils.isEmpty(header.getTransactionId())) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 生成返回消息头
	 * @param code
	 * @return
	 */
	public Result generateResult(String code) {
		Result result = null;
		Pattern p = Pattern.compile(HelloConstant.REG);
		if(null == code || !p.matcher(code).matches()) {
			result = new Result(HelloConstant.RESULT_CODE_ERROR,HelloConstant.RESULT_MSG_PARAM_ERROR);
		}else {
			result = new Result(HelloConstant.RESULT_CODE_SUCCESS,HelloConstant.RESULT_MSG_SUCCESS);
		}
		return result;
	}
}
