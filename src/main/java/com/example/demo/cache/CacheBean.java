package com.example.demo.cache;

/**
 * 缓存数据传输Bean
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.cache   
* 类名称:    CacheBean.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月18日
 */
public class CacheBean {

	private String mapKey;
	
	private String fieldKey;
	
	private Object fieldValue;
	
	private String operate;
	
	private String operateMode;
	
	private boolean isNeverExpire;

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	public boolean isNeverExpire() {
		return isNeverExpire;
	}

	public void setNeverExpire(boolean isNeverExpire) {
		this.isNeverExpire = isNeverExpire;
	}
}
