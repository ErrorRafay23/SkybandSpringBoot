package com.skyband.base;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.skyband.exception.MyException;


public interface ECRCore {

	public String doTCPIPTransaction(String ipAddress, int portNumber, String requestData, int transactionType,
			String signature) throws MyException, InterruptedException;

	public String doCOMTransaction(int portNumber, String baudRate, int parity, int dataBits, int stopBits,
			String requestData, int transactionType, String signature) throws MyException, IOException;

	public int doTCPIPConnection(String ipAddress, int portNumber) throws IOException;

	public int doDisconnection(String ipAddress, int portNumber) throws IOException;//

	public int doCOMDisconnection() throws IOException;

	public int doCOMConnection(int portNumber, String baudRate, int parity, int dataBits, int stopBits);
	
	public String computeSha256Hash(String signature) throws NoSuchAlgorithmException;

}
