package com.skyband.skybandecr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.fazecast.jSerialComm.SerialPort;
import com.skyband.exception.MyException;
import com.skyband.util.*;

public class SerialConnectionManager {

	private static Logger logger = Logger.getLogger(SerialConnectionManager.class.getName());

	private String portName;
	private String baudRate;
	private int parity;
	private int dataBits;
	private int stopBits;
	static SerialPort serialPort;
	private static SerialConnectionManager serialHostConnector = null;
	private static Integer serialConnectionFlag = 0;
	Properties properties = new Properties();

	private InputStream inputStream;

	private OutputStream outputStream;

	private SerialConnectionManager(String portName, String baudRate, int parity, int dataBit, int stopBit) {
		this.portName = portName;
		this.baudRate = baudRate;
		this.parity = parity;
		this.dataBits = dataBit;
		this.stopBits = stopBit;
	}

	public static boolean instance(Integer portNo, String baudRate, int parity, int dataBit, int stopBit) {
		logger.info("Class :: SerialConnectionManager :: Method Instances {Entered}");
		
		String serialComm = "";
		String osType = System.getProperty("os.name");
		if (osType.contains("Linux")) {
			serialComm = "ttyACM";
		} else if (osType.contains("Windows")) {
			serialComm = "COM";
		}
		setSerialHostConnector(
				new SerialConnectionManager(serialComm + portNo.toString(), baudRate, parity, dataBit, stopBit));
//		setSerialHostConnector(
//				new SerialConnectionManager("COM" + portNo.toString(), baudRate, parity, dataBit, stopBit));
		logger.info("Class :: SerialConnectionManager :: Method Instances {Exit}");
		return getSerialHostConnector().createSerialConnection();
	}

	public static SerialConnectionManager instance() {
		if (getSerialHostConnector() != null) {
			return getSerialHostConnector();
		}
		return null;
	}

	/**
	 * This use only for exiting connection access
	 * 
	 * @return
	 * @throws IOException
	 */

	public void disconnect() throws IOException {

		logger.info("SerialConnectionManager | Disconnect | Entering");
		close();
		setSerialConnectionFlag(0);
		logger.info("SerialConnectionManager | Disconnect | Exiting");

	}

	public void close() throws IOException {
		logger.info("SerialConnectionManager | Close | Entering");
		if (serialPort != null) {
			serialPort.closePort();
			inputStream.close();
			outputStream.close();
			logger.info("###################### " + serialPort + " Serial Port Closed  ###################### ");
		}
		logger.info("SerialConnectionManager | Close | Exiting");
	}

	public boolean createSerialConnection() {
		logger.info("SerialConnectionManager | createSerialConnection | Entering");
		serialPort = SerialPort.getCommPort(portName);
		if (serialPort != null) {
			serialPort.setComPortParameters(Integer.parseInt(this.baudRate), this.dataBits, this.stopBits, this.parity);
			setSerialConnectionFlag(1);
			inputStream = serialPort.getInputStream();
			outputStream = serialPort.getOutputStream();
			return serialPort.openPort();
		}
		logger.info("SerialConnectionManager | createSerialConnection | Exiting");
		return false;
	}

	public static boolean checkComConnection() {
		return serialPort.openPort();
	}

	public void sendData(byte[] packData) throws IOException {
		logger.info("SerialConnectionManager | SendData | Entering");
		outputStream.write(packData);
		outputStream.flush();
		logger.info("SerialConnectionManager | SendData | Exiting");
	}

	public String receiveData() throws IOException, InterruptedException, MyException {
		logger.info("SerialConnectionManager | Receive | Entering");
		String returnData = "";
		StringBuilder response = new StringBuilder();
		int count = 0;
		byte[] reSizeTerminalResponse = null;
		byte[] terminalResponse = new byte[50000];
		long startTime = System.currentTimeMillis();
		long totalTime;
		do {
			logger.info("Waiting...");
			Thread.sleep(1l);
			if (inputStream.available() > 0) {
				count = inputStream.read(terminalResponse, 0, terminalResponse.length);
			}
			logger.info("Count:" + count);
			long endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			if (totalTime >= 120000) {
				throw new MyException("TimedOut");
			}
		} while (count < 5);
		reSizeTerminalResponse = terminalResponse;
		response.append(CommonUtils.byteArrayToHexString(reSizeTerminalResponse));
		returnData = response.toString();
		logger.info("Serial Response" + CommonUtils.hexToString(returnData));

		logger.info("SerialConnectionManager | Receive | Exiting");

		return CommonUtils.hexToString(returnData);

	}

	public static SerialConnectionManager getSerialHostConnector() {
		return serialHostConnector;
	}

	public static void setSerialHostConnector(SerialConnectionManager serialHostConnector) {
		SerialConnectionManager.serialHostConnector = serialHostConnector;
	}

	public static Integer getSerialConnectionFlag() {
		return serialConnectionFlag;
	}

	public static void setSerialConnectionFlag(Integer serialConnectionFlag) {
		SerialConnectionManager.serialConnectionFlag = serialConnectionFlag;
	}

	public void propertiesIntialization() {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException ex) {
			logger.warning("Class ConnectionManager :: propertiesIntialization method :" + ex);
		}
	}
}
