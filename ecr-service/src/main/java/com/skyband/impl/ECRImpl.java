package com.skyband.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import com.skyband.base.ECRCore;
import com.skyband.exception.MyException;
import com.skyband.skybandecr.CLibraryLoad;
import com.skyband.skybandecr.ConnectionManager;
import com.skyband.skybandecr.SerialConnectionManager;


public class ECRImpl implements ECRCore {

	Logger logger = Logger.getLogger(ECRImpl.class.getName());

	public static final Integer ONE = 1;
	public static final Integer ZERO = 0;

	public static ECRCore getConnectInstance() {
		return new ECRImpl();
	}

	@Override
	public int doTCPIPConnection(String ipAddress, int portNumber) throws IOException {

		logger.info("ECRImpl | DoTCPIPConnection | Entering");
		if (ConnectionManager.instance(ipAddress, portNumber).isConnected()) {
			logger.info("ECRImpl | DoTCPIPConnection | Exiting1");
			return ZERO;
		} else {
			logger.info("ECRImpl | DoTCPIPConnection | Exiting2");

			return ONE;
		}
	}

	@Override
	public int doDisconnection(String ipAddress, int portNumber) throws IOException {

		logger.info("ECRImpl | DoDisconnection | Entering");
		ConnectionManager exinstance = ConnectionManager.instance();
		if(exinstance!=null){
			exinstance.cleanup();
			exinstance.disconnect();
			return 0;
		}else{
			return 1;
		}

		// if (ConnectionManager.instance() != null) {
		// 	ConnectionManager.instance().disconnect();
		// 	return 0;
		// } else {
		// 	return 1;
		// }

	}

	@Override
	public String doCOMTransaction(int portNumber, String baudRate, int parity, int dataBits, int stopBits,
			String requestData, int transactionType, String signature) throws MyException, IOException {
		SerialConnectionManager serialHostConnector = SerialConnectionManager.getSerialHostConnector();
		if (SerialConnectionManager.getSerialConnectionFlag() != 1) {
			try {
				serialHostConnector.createSerialConnection();
			} catch (Exception e) {
				throw new MyException("UnableToConnect");
			}

		}

		String terminalResponse = "";

		byte[] packData = CLibraryLoad.getInstance().getPackData(requestData, transactionType, signature);
		if (serialHostConnector != null) {
			try {
				serialHostConnector.sendData(packData);
				terminalResponse = serialHostConnector.receiveData();
			} catch (IOException e) {
				logger.info("ECRImpl :: doCOMTransaction :: IOException"+ e.getMessage());

			} catch (Exception e) {
				logger.info("ECRImpl :: doCOMTransaction :: Exception"+ e.getMessage());
				if (e.getMessage().equals("TimedOut")) {
					serialHostConnector.disconnect();
					throw new MyException("TimedOutMessage");
				}
			}
		}

		String osType = System.getProperty("os.name");
		if (osType.contains("Linux")) {
			terminalResponse = terminalResponse.replace("ü", ";");
		} else if (osType.contains("Windows")) {
			terminalResponse = terminalResponse.replace("ü", ";");
		}

		terminalResponse = changeToTransactionType(terminalResponse);

		return terminalResponse;
	}

	@Override
	public String doTCPIPTransaction(String ipAddress, int portNumber, String requestData, int transactionType,
			String signature) throws MyException, InterruptedException {

		String terminalResponse = "";
		try {
			ECRImpl.getConnectInstance().doTCPIPConnection(ipAddress, portNumber);

		} catch (Exception e) {
			throw new MyException("3");
		}
		byte[] packData = CLibraryLoad.getInstance().getPackData(requestData, transactionType, signature);
		logger.info("bytePackData in ECRImpl is "+ packData);
		if (ConnectionManager.instance() != null && ConnectionManager.instance().isConnected()) {
			try {
				if(transactionType!=22){
					terminalResponse = ConnectionManager.instance().sendAndRecv(packData);
					logger.info("terminalresponsetry in ECRImpl is "+ terminalResponse);}
				else
					terminalResponse = ConnectionManager.instance().sendAndRecvSummary(packData);
                logger.info("Terminal Response in ECRImpl is "+ terminalResponse);
				logger.info("PackData in ECRImpl is "+ packData);
			} catch (IOException e) {
				try {
					ECRImpl.getConnectInstance().doDisconnection(ipAddress, portNumber);
					logger.info("DisConnectedSuccessful");
					throw new MyException("0");
				} catch (IOException ex) {
					throw new MyException("1");
				}
			}
		} else {
			throw new MyException("2");
		}
		String osType = System.getProperty("os.name");
		if (osType.contains("Linux")) {
			terminalResponse = terminalResponse.replace("�", ";");
		} else if (osType.contains("Windows")) {
			terminalResponse = terminalResponse.replace("ü", ";");
		}
		terminalResponse = changeToTransactionType(terminalResponse);
		logger.info("TerminalResponse:"+terminalResponse);

		return terminalResponse;
	}

	private String changeToTransactionType(String terminalResponse) {
		String[] response = terminalResponse.split(";");
		switch (response[1]) {
		case "A0":
			terminalResponse = terminalResponse.replaceFirst("A0", String.valueOf(17));
			logger.info("A0" + terminalResponse);
			break;
		case "B6":
			terminalResponse = terminalResponse.replaceFirst("B6", String.valueOf(18));
			break;
		case "B7":
			terminalResponse = terminalResponse.replaceFirst("B7", String.valueOf(19));
			break;
		case "A1":
			terminalResponse = terminalResponse.replaceFirst("A1", String.valueOf(0));
			break;
		case "A2":
			terminalResponse = terminalResponse.replaceFirst("A2", String.valueOf(1));
			break;
		case "A3":
			terminalResponse = terminalResponse.replaceFirst("A3", String.valueOf(8));
			break;
		case "A4":
			terminalResponse = terminalResponse.replaceFirst("A4", String.valueOf(3));
			break;
		case "A5":
			terminalResponse = terminalResponse.replaceFirst("A5", String.valueOf(9));
			break;
		case "A6":
			terminalResponse = terminalResponse.replaceFirst("A6", String.valueOf(2));
			break;
		case "A7":
			terminalResponse = terminalResponse.replaceFirst("A7", String.valueOf(4));
			break;
		case "A8":
			terminalResponse = terminalResponse.replaceFirst("A8", String.valueOf(5));
			break;
		case "A9":
			terminalResponse = terminalResponse.replaceFirst("A9", String.valueOf(6));
			break;
		case "B1":
			terminalResponse = terminalResponse.replaceFirst("B1", String.valueOf(10));
			break;
		case "B2":
			terminalResponse = terminalResponse.replaceFirst("B2", String.valueOf(11));
			break;
		case "B3":
			terminalResponse = terminalResponse.replaceFirst("B3", String.valueOf(12));
			break;
		case "B4":
			terminalResponse = terminalResponse.replaceFirst("B4", String.valueOf(13));
			break;
		case "B5":
			terminalResponse = terminalResponse.replaceFirst("B5", String.valueOf(14));
			break;
		case "B8":
			terminalResponse = terminalResponse.replaceFirst("B8", String.valueOf(20));
			break;
		case "B9":
			terminalResponse = terminalResponse.replaceFirst("B9", String.valueOf(21));
			break;
		case "C1":
			terminalResponse = terminalResponse.replaceFirst("C1", String.valueOf(22));
			break;
		case "C2":
			terminalResponse = terminalResponse.replaceFirst("C2", String.valueOf(23));
			break;
		case "C3":
			terminalResponse = terminalResponse.replaceFirst("C3", String.valueOf(24));
			break;
		default:
			logger.info("Invalid Response code");
		}
		return terminalResponse;
	}

	@Override
	public int doCOMConnection(int portNumber, String baudRate, int parity, int dataBits, int stopBits)  {
		boolean flag = SerialConnectionManager.instance(portNumber, baudRate, parity, dataBits, stopBits);
		if (flag) {
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int doCOMDisconnection() throws IOException {
		SerialConnectionManager serialHostConnector = SerialConnectionManager.getSerialHostConnector();
		serialHostConnector.disconnect();
		return 0;
	}

	@Override
	public String computeSha256Hash(String signature) throws NoSuchAlgorithmException {
		logger.info("SHAConvrsion | ConvertSHA | Entering");

		MessageDigest md = MessageDigest.getInstance("SHA-256");    
		byte[] hashInBytes = md.digest(signature.getBytes(StandardCharsets.ISO_8859_1));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		String resultSHA = sb.toString();

		logger.info("SHAConvrsion | ConvertSHA | Exiting");

		return resultSHA;
	}
	
	
}
