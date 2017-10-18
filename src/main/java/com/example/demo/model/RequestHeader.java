package com.example.demo.model;

public class RequestHeader {

	private String version;//版本号
	private String transactionId;//业务ID
	private String productLine;//产品线
	private String portalType;//入口类型
	private String platform;//平台
	private String companyId;//公司ID
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getPortalType() {
		return portalType;
	}
	public void setPortalType(String portalType) {
		this.portalType = portalType;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
