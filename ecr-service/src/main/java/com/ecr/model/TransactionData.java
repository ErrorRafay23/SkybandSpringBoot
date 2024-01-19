package com.ecr.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
	

public class TransactionData extends Response {
		
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	private String inputReqData;
	
	private String transaction;

	private String Ip;//

    private int portNumber;//

	private String isConnected;//

	private Boolean connectionStatus;//

	public String getInputReqData() {
		return inputReqData;
	}

	public void setInputReqData(String inputReqData) {
		this.inputReqData = inputReqData;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

public String getipAddress() {//
		return Ip;
	}

	public void setipAddress(String Ip) {//
		this.Ip = Ip;
	}

public int getportNumber() {//
		return portNumber;
	}

	public void setport(int portNumber) {//
		this.portNumber = portNumber;
	}

public String getisConnected() {//
		return isConnected;
	}

	public void setisConnected(String isConnected) {//
		this.isConnected = isConnected;
	}


	public Boolean getconnectionStatus() {
        return connectionStatus;
    }

    public void setconnectionStatus(Boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

	public TransactionData(String inputReqData, String transaction, String Ip, int portNumber, String isConnected, Boolean connectionStatus) {//
		super();
		this.inputReqData = inputReqData;
		this.transaction = transaction;
		this.Ip = Ip;
		this.portNumber = portNumber;
		this.isConnected = isConnected;
		this.connectionStatus = connectionStatus;
	}

	public TransactionData() {
		super();
	}

	@Override
	public String toString() {
		logger.info("Portcheck " + portNumber);
		return "TransactionData [ip=" + Ip + ",port=" + portNumber + ",Connection_Status" + isConnected +",inputReqData=" + inputReqData + ", transaction=" + transaction + "]";
	}
}
