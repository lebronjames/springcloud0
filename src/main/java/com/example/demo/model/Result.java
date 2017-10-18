package com.example.demo.model;

public class Result {

	private String resultCode;
	private String resultMsg;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public Result(){
		
	}
	
	public Result(String resultCode, String resultMsg){
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
}
