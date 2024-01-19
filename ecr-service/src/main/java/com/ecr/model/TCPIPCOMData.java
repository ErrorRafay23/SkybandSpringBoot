package com.ecr.model;

public class TCPIPCOMData {

	private String ip;

	private int port;

	private String baudRate;

	private int parity;

	private int dataBits;

	private int stopBits;

	public TCPIPCOMData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TCPIPCOMData(String ip, int port, String baudRate, int parity, int dataBits, int stopBits) {
		super();
		this.ip = ip;
		this.port = port;
		this.baudRate = baudRate;
		this.parity = parity;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
	}

	public String getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(String baudRate) {
		this.baudRate = baudRate;
	}

	public int getParity() {
		return parity;
	}

	public void setParity(int parity) {
		this.parity = parity;
	}

	public int getDataBits() {
		return dataBits;
	}

	public void setDataBits(int dataBits) {
		this.dataBits = dataBits;
	}

	public int getStopBits() {
		return stopBits;
	}

	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
