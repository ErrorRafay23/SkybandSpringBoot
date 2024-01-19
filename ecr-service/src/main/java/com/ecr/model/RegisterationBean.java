package com.ecr.model;



public class RegisterationBean {

	String ipAddress;
	int portNo;
	String inputReqData;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPortNo() {
		return portNo;
	}
	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}
	public String getInputReqData() {
		return inputReqData;
	}
	public void setInputReqData(String inputReqData) {
		this.inputReqData = inputReqData;
	}
	
	@Override
	public String toString() {
		return "RegisterationBean [ipAddress=" + ipAddress + ", portNo=" + portNo + ", inputReqData=" + inputReqData
				+ "]";
	}
	public RegisterationBean(String ipAddress, int portNo, String inputReqData) {
		super();
		this.ipAddress = ipAddress;
		this.portNo = portNo;
		this.inputReqData = inputReqData;
	}
	
}
