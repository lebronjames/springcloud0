package com.example.demo.model;

import java.util.List;

import com.example.demo.dto.DemoDto;

public class HelloResponseModel {

	private Result result;
	private List<DemoDto> list;
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public List<DemoDto> getList() {
		return list;
	}
	public void setList(List<DemoDto> list) {
		this.list = list;
	}

}
