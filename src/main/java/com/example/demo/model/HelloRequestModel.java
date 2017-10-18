package com.example.demo.model;

import java.util.List;

public class HelloRequestModel {

	private RequestHeader header;
	private List<String> list;
	
	public RequestHeader getHeader() {
		return header;
	}
	public void setHeader(RequestHeader header) {
		this.header = header;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
}