package com.ecr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecr.exception.PaymentValidationException;
import com.ecr.model.PaymentRequest;
import com.ecr.model.TCPIPCOMData;
import com.ecr.model.TransactionData;
import com.ecr.service.PaymentService;
import com.ecr.validation.PaymentRequestValidator;
import com.skyband.base.ECRCore;
import com.skyband.impl.ECRImpl;
import com.ecr.wrapper.TransactionRequest;

@RestController
@CrossOrigin(origins = {"${cors.urls}"})
@PropertySource("classpath:application.properties")
public class MainController {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	PaymentRequestValidator paymentRequestValidator;
	
	 String Ip="";
	 int port;
	 int portNumber;
	 String baudRate="";
	 int parity ;
	 int dataBits;
	 int stopBits;

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	
	/*@PostMapping("/tcp-ip/connect")
	public int connectTCPIP(@RequestBody TCPIPCOMData tcpData) {
		logger.info("connectTCPIP | Entering");
		ECRCore ecrCore = ECRImpl.getConnectInstance();
		try {
			
			int returnValue = ecrCore.doTCPIPConnection(tcpData.getIp(), tcpData.getPort());
			Ip = tcpData.getIp();
			port = tcpData.getPort();

			if (returnValue == 0)
				System.out.println("Connection Established");
			else
				System.out.println("Connection failed");
			return returnValue;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("connectTCPIP :: Exception" + e.getMessage());
			return 1;
		}
	}*/

	@PostMapping("/com/connect")
	public int connectCOM(@RequestBody TCPIPCOMData comData) {
		logger.info("connectCOM | Entering");
		ECRCore ecrCore = ECRImpl.getConnectInstance();
		
		try {
			int returnValue = ecrCore.doCOMConnection(comData.getPort(), comData.getBaudRate(), comData.getParity(), comData.getDataBits(), comData.getStopBits());
			portNumber = comData.getPort();
			baudRate = comData.getBaudRate();
			parity = comData.getParity();
			dataBits= comData.getDataBits();
			stopBits = comData.getStopBits();
			return returnValue;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("connectCOM :: Exception" + e.getMessage());
			return 1;
		}
	}

	@PostMapping("/tcp-ip/disconnect")
	public TransactionData disconnectTCPIP(@RequestBody TCPIPCOMData tcpData) {
		logger.info("disconnectTCPIP | Entering");
		ECRCore ecrCore = ECRImpl.getConnectInstance();
		TransactionData response = new TransactionData();
		Boolean connectionStatus = false; 
		try {
			int disconnectStatus = ecrCore.doDisconnection(tcpData.getIp(), tcpData.getPort());
			response.setipAddress(Ip);
			response.setport(port);
			//response.setisConnected("false");
			response.setconnectionStatus(connectionStatus);
			//response.setDisconnectStatus(String.valueOf(disconnectStatus));
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("disconnectTCPIP :: Exception" + e.getMessage());
			response.setisConnected("true");
			response.setipAddress(tcpData.getIp());
			response.setport(tcpData.getPort());
			//response.setDisconnectStatus("1"); // Assuming 1 is an error status, you can adjust it based on your implementation
			return response;
		}
	}


	/*public int disconnectTCPIP(TCPIPCOMData tcpData) {
		logger.info("disconnectTCPIP | Entering");
		ECRCore ecrCore = ECRImpl.getConnectInstance();
		try {
			int disConnectStatus = ecrCore.doDisconnection(tcpData.getIp(), tcpData.getPort());
			return disConnectStatus;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("disconnectTCPIP :: Exception" + e.getMessage());
			return 1;
		}
	}*/

	@PostMapping("/performTransactionTCPIP")
	public TransactionData performTransactionTCPIP(@RequestBody TransactionRequest transactionRequest) throws IOException {
		try {
			PaymentRequest paymentRequest = transactionRequest.getPaymentRequest();
			TCPIPCOMData tcpData = transactionRequest.getTcpipcomData();
	
			// Connect to TCPIP before performing the transaction
			int connectStatus = connectTCPIP(tcpData);
			//logger.info(tcpData.getIp());
			if (connectStatus != 0) {
				// Handle connection failure
				TransactionData response = new TransactionData();
				TransactionData transactionData = new TransactionData();
				transactionData.setipAddress(tcpData.getIp());
        		transactionData.setport(tcpData.getPort());
        		transactionData.setisConnected("Connected");
				response.setStatusMessage("Failed to establish TCPIP connection.");
				response.setStatusCode(String.valueOf(connectStatus));
				return response;
			}
	
			// Validate payment request and perform TCPIP transaction
			paymentRequestValidator.validatePaymentRequest(paymentRequest);
			logger.info(Ip);
			logger.info(port);
			return paymentService.performTransactionTCPIP(paymentRequest, Ip, port);
		} catch (PaymentValidationException pe) {
			TransactionData response = new TransactionData();
			response.setStatusMessage(pe.getErrorMessage());
			response.setStatusCode(pe.getErrorCode());
			return response;
		}
	}
	

			public int connectTCPIP(TCPIPCOMData tcpData) {
			logger.info("connectTCPIP | Entering");
			ECRCore ecrCore = ECRImpl.getConnectInstance();
			try {
				int returnValue = ecrCore.doTCPIPConnection(tcpData.getIp(), tcpData.getPort());
				Ip = tcpData.getIp();
				port = tcpData.getPort();
	
				if (returnValue == 0)
					System.out.println("Connection Established");
				else
					System.out.println("Connection failed");
				return returnValue;
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("connectTCPIP :: Exception" + e.getMessage());
				return 1;
			}
	}
	
	@PostMapping("/performTransactionCOM")
	public TransactionData performTransactionCOM(@RequestBody PaymentRequest paymentRequest) {
		try {
			paymentRequestValidator.validatePaymentRequest(paymentRequest);
			return paymentService.performTransactionCOM(paymentRequest, portNumber,baudRate,parity,dataBits,stopBits);
		} catch (PaymentValidationException e) {
			TransactionData response = new TransactionData();
			response.setStatusMessage(e.getErrorMessage());
			response.setStatusCode(e.getErrorCode());
			return response;
		}
	}
	
	@PostMapping("/com/disconnect")
	public int disconnectCOM() {
		logger.info("disconnectCOM | Entering");
		ECRCore ecrCore = ECRImpl.getConnectInstance();
		try {
			int disConnectStatus = ecrCore.doCOMDisconnection();
			return disConnectStatus;
		} catch (IOException e) {
			logger.info("disconnectCOM :: Exception" + e.getMessage());
			return 1;
		}
	}

}
