package com.ecr.enums;

public enum StatusCodes {
	ERROR_GENERIC("100"),
	ERROR_INALID_DATA("101"),
	ERROR_PROCESSING_TRANSACTION("102"),
	
	SUCCESS("200");
	
	private String code;
	
	public String getCode() {
		return this.code;
	}
	
	StatusCodes(String code) {
		this.code = code;
	}
}
