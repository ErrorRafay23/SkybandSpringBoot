package com.ecr.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ecr.enums.StatusCodes;
import com.ecr.enums.TransactionType;
import com.ecr.exception.PaymentValidationException;
import com.ecr.model.PaymentRequest;
import com.ecr.model.TransactionData;
import com.skyband.base.ECRCore;
import com.skyband.impl.ECRImpl;


@Service
@PropertySource("classpath:application.properties")
public class PaymentService {

	private String signature = "0000000000000000000000000000000000000000000000000000000000000000";

	private String terminalID = "";

	private Logger logger = LogManager.getLogger(this.getClass().getName());

	private final String className = this.getClass().getSimpleName();

	public TransactionData performTransactionTCPIP(PaymentRequest paymentRequest, String ipAddress, int portNumber)
			throws PaymentValidationException {
		ECRCore ecrCore = ECRImpl.getConnectInstance();
		logger.info("Coreman " + ecrCore);
		PaymentRequest req = new PaymentRequest();
		String reqData = "";
		String isConnected = "";
		Boolean connectionStatus = true;
		TransactionData transactionData = new TransactionData();
		try {
			switch (paymentRequest.getTxnType()) {
			case PURCHASE:
				req.setDate(paymentRequest.getDate());
				req.setAmount(paymentRequest.getAmount());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getAmount() + ";" + req.getPrint() + ";" + req.getEcrReferenceNo()
						+ "!";
				signature = ecrCore.computeSha256Hash(getEcrRefNumber(req) + terminalID);
				String responsePurchase = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.PURCHASE.getTransactionCode(), signature);
				transactionData.setTransaction(responsePurchase);
				transactionData.setipAddress(ipAddress);
				transactionData.setport(portNumber);
				transactionData.setconnectionStatus(connectionStatus);
				break;
			case UPI:
				req.setDate(paymentRequest.getDate());
				req.setAmount(paymentRequest.getAmount());
				req.setCashBackAmount(paymentRequest.getCashBackAmount());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getAmount() + ";" + req.getCashBackAmount() + ";" + req.getPrint()
						+ ";" + req.getEcrReferenceNo() + "!";
				String responseUpi = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.UPI.getTransactionCode(), signature);
				transactionData.setTransaction(responseUpi);
				break;
			case BRAND_EMI:
				req.setDate(paymentRequest.getDate());
				req.setRefundAmount(paymentRequest.getRefundAmount());
				req.setRrnNumber(paymentRequest.getRrnNumber());
				req.setPrint(paymentRequest.getPrint());
				req.setOriginalRefundDate(paymentRequest.getOriginalRefundDate());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getRefundAmount() + ";" + req.getRrnNumber() + ";" + req.getPrint()
						+ ";" + req.getOriginalRefundDate() + ";" + req.getEcrReferenceNo() + "!";
				String responseBrandUpi = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.BRAND_EMI.getTransactionCode(), signature);
				transactionData.setTransaction(responseBrandUpi);
				break;
			case AUTHORIZATION:
				req.setDate(paymentRequest.getDate());
				req.setAuthAmount(paymentRequest.getAuthAmount());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getAuthAmount() + ";" + req.getPrint() + ";"
						+ req.getEcrReferenceNo() + "!";
				String responseAuthorization = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.AUTHORIZATION.getTransactionCode(), signature);
				transactionData.setTransaction(responseAuthorization);
				break;
			case PURCHASE_ADVICE_FULL:
				req.setDate(paymentRequest.getDate());
				req.setAuthAmount(paymentRequest.getAuthAmount());
				req.setRrnNumber(paymentRequest.getRrnNumber());
				req.setOrigTransactionDate(paymentRequest.getOrigTransactionDate());
				req.setOrigApproveCode(paymentRequest.getOrigApproveCode());
				req.setCapture(paymentRequest.getCapture());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getAuthAmount() + ";" + req.getRrnNumber() + ";"
						+ req.getOrigTransactionDate() + ";" + req.getOrigApproveCode() + ";" + req.getCapture() + ";"
						+ req.getPrint() + ";" + req.getEcrReferenceNo() + "!";
				String responsePurchaseAdviceFull = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.PURCHASE_ADVICE_FULL.getTransactionCode(), signature);
				transactionData.setTransaction(responsePurchaseAdviceFull);
				break;
			case AUTHORIZATION_EXTENSION:
				req.setDate(paymentRequest.getDate());
				req.setRrnNumber(paymentRequest.getRrnNumber());
				req.setOrigTransactionDate(paymentRequest.getOrigTransactionDate());
				req.setOrigApproveCode(paymentRequest.getOrigApproveCode());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getRrnNumber() + ";" + req.getOrigTransactionDate() + ";"
						+ req.getOrigApproveCode() + ";" + req.getPrint() + ";" + req.getEcrReferenceNo() + "!";
				String responseAuthExtension = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.AUTHORIZATION_EXTENSION.getTransactionCode(), signature);
				transactionData.setTransaction(responseAuthExtension);
				break;
			case AUTHORIZATION_VOID:
				req.setDate(paymentRequest.getDate());
				req.setOrigTransactionAmt(paymentRequest.getOrigTransactionAmt());
				req.setRrnNumber(paymentRequest.getRrnNumber());
				req.setOrigTransactionDate(paymentRequest.getOrigTransactionDate());
				req.setOrigApproveCode(paymentRequest.getOrigApproveCode());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getOrigTransactionAmt() + ";" + req.getRrnNumber() + ";"
						+ req.getOrigTransactionDate() + ";" + req.getOrigApproveCode() + ";" + req.getPrint() + ";"
						+ req.getEcrReferenceNo() + "!";
				String responseAuthVoid = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.AUTHORIZATION_VOID.getTransactionCode(), signature);
				transactionData.setTransaction(responseAuthVoid);
				break;
			case CASH_ADVANCE:
				req.setDate(paymentRequest.getDate());
				req.setCashAdvanceAmt(paymentRequest.getCashAdvanceAmt());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getCashAdvanceAmt() + ";" + req.getPrint() + ";"
						+ req.getEcrReferenceNo() + "!";
				String responseCashAdvance = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.CASH_ADVANCE.getTransactionCode(), signature);
				transactionData.setTransaction(responseCashAdvance);
				break;
			case REVERSAL:
			case RECONCILIATION:
				req.setDate(paymentRequest.getDate());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getPrint() + ";" + req.getEcrReferenceNo() + "!";
				String responseReconciliation = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.RECONCILIATION.getTransactionCode(), signature);
				transactionData.setTransaction(responseReconciliation);
				break;
			case SET_SETTINGS:
				req.setDate(paymentRequest.getDate());
				req.setVendorId(paymentRequest.getVendorId());
				req.setVendorTerminalType(paymentRequest.getVendorTerminalType());
				req.setTrsmId(paymentRequest.getTrsmId());
				req.setVendorKeyIndex(paymentRequest.getVendorKeyIndex());
				req.setSamaKeyIndex(paymentRequest.getSamaKeyIndex());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getVendorId() + ";" + req.getVendorTerminalType() + ";"
						+ req.getTrsmId() + ":" + req.getVendorKeyIndex() + ";" + req.getSamaKeyIndex() + ";"
						+ req.getEcrReferenceNo() + "!";
				String responseSetSettings = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.SET_SETTINGS.getTransactionCode(), signature);
				transactionData.setTransaction(responseSetSettings);
				break;
			case SET_TERMINAL_LANGUAGE:
				req.setDate(paymentRequest.getDate());
				req.setTerminalLanguage(paymentRequest.getTerminalLanguage());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getTerminalLanguage() + ";" + req.getEcrReferenceNo() + "!";
				String responseSetTerminalLanguage = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.SET_TERMINAL_LANGUAGE.getTransactionCode(), signature);
				transactionData.setTransaction(responseSetTerminalLanguage);
				break;
			case BILL_PAYMENT:
				req.setDate(paymentRequest.getDate());
				req.setBillPayAmt(paymentRequest.getBillPayAmt());
				req.setBillerId(paymentRequest.getBillerId());
				req.setBillerNumber(paymentRequest.getBillerNumber());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getBillPayAmt() + ";" + req.getBillerId() + ";"
						+ req.getBillerNumber() + ";" + req.getPrint() + ";" + req.getEcrReferenceNo() + "!";
				String responseBillPayment = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.BILL_PAYMENT.getTransactionCode(), signature);
				transactionData.setTransaction(responseBillPayment);
				break;
			case REGISTER:
				req.setDate(paymentRequest.getDate());
				req.setCashRegisterNo(paymentRequest.getCashRegisterNo());
				reqData = req.getDate() + ";" + req.getCashRegisterNo() + "!";
				String responseRegister = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.REGISTER.getTransactionCode(), signature);
				terminalID = getTerminalId(responseRegister);
				transactionData.setTransaction(responseRegister);
				transactionData.setipAddress(ipAddress);
				transactionData.setport(portNumber);
				//boolean connectionStatus = ConnectionManager.instance(ipAddress, portNumber);
				transactionData.setconnectionStatus(connectionStatus);
				break;
			case START_SESSION:
				req.setDate(paymentRequest.getDate());
				req.setCashRegisterNo(paymentRequest.getCashRegisterNo());
				reqData = req.getDate() + ";" + req.getCashRegisterNo() + "!";
				String responseStartSession = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.START_SESSION.getTransactionCode(), signature);
				transactionData.setTransaction(responseStartSession);
				break;
			case END_SESSION:
				req.setDate(paymentRequest.getDate());
				req.setCashRegisterNo(paymentRequest.getCashRegisterNo());
				reqData = req.getDate() + ";" + req.getCashRegisterNo() + "!";
				String responseEndSession = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.END_SESSION.getTransactionCode(), signature);
				transactionData.setTransaction(responseEndSession);
				break;
			case DUPLICATE:
				req.setDate(paymentRequest.getDate());
				req.setPrevEcrNo(paymentRequest.getPrevEcrNo());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getPrevEcrNo() + ";" + req.getEcrReferenceNo() + "!";
				String responseDuplicate = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.DUPLICATE.getTransactionCode(), signature);
				transactionData.setTransaction(responseDuplicate);
				transactionData.setipAddress(ipAddress);
				transactionData.setport(portNumber);
				transactionData.setconnectionStatus(connectionStatus);
				break;
			case PRINT_SUMMARY_REPORT:
				req.setDate(paymentRequest.getDate());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getEcrReferenceNo() + "!";
				String responsePrintSummaryReport = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.PRINT_SUMMARY_REPORT.getTransactionCode(), signature);
				transactionData.setTransaction(responsePrintSummaryReport);
				break;
			case FULL_DOWNLOAD:
			case PARTIAL_DOWNLOAD:
			case GET_SETTINGS:
			case TERMINAL_STATUS:
			case PREVIOUS_TRANSACTION_DETAILS:
			case RUNNING_TOTAL:
			case SNAPSHOT_TOTAL:
			case CHECK_STATUS:
				req.setDate(paymentRequest.getDate());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getEcrReferenceNo() + "!";
				signature = ecrCore.computeSha256Hash(getEcrRefNumber(req) + terminalID);
				String responseCheckStatus = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData,
						TransactionType.CHECK_STATUS.getTransactionCode(), signature);
				transactionData.setTransaction(responseCheckStatus);
				transactionData.setipAddress(ipAddress);
				transactionData.setport(portNumber);
				transactionData.setconnectionStatus(connectionStatus);
				break;

			default:
				req.setDate(paymentRequest.getDate());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getEcrReferenceNo() + "!";
				String response = ecrCore.doTCPIPTransaction(ipAddress, portNumber, reqData, 0, signature);
				transactionData.setTransaction(response);
				transactionData.setipAddress(ipAddress);
				transactionData.setport(portNumber);
				transactionData.setconnectionStatus(connectionStatus);
				break;

			}

		} catch (Exception e) {
			PaymentValidationException exception = new PaymentValidationException(e);
			exception.setErrorMessage("Unable to process the request " + e.getMessage());
			exception.setErrorCode(StatusCodes.ERROR_PROCESSING_TRANSACTION.getCode());
			logger.error("Unable to process the request", className, e);
			throw exception;
		}
		return transactionData;
	}

	public String getTerminalId(String terminalResponseString) {
		String[] splittedArray = terminalResponseString.split(";");

		for (int i = 0; i < splittedArray.length; i++) {
			if (i == 3) {
				terminalID = splittedArray[i];
			}
		}
		return terminalID;

	}
	
	public String getEcrRefNumber(PaymentRequest req) {
		String s1 = req.getEcrReferenceNo();
		String s2 = s1.substring(s1.length() -6);
		return s2;
	}
	
	

	public TransactionData performTransactionCOM(PaymentRequest paymentRequest, int port, String baudRate, int parity,
			int dataBits, int stopBits) throws PaymentValidationException {

		ECRCore ecrCore = ECRImpl.getConnectInstance();
		PaymentRequest req = new PaymentRequest();
		String reqData = "";
		TransactionData transactionData = new TransactionData();
		try {
			switch (paymentRequest.getTxnType()) {
			case PURCHASE:
				req.setDate(paymentRequest.getDate());
				req.setAmount(paymentRequest.getAmount());
				req.setPrint(paymentRequest.getPrint());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getAmount() + ";" + req.getPrint() + ";" + req.getEcrReferenceNo()
						+ "!";
				signature = ecrCore.computeSha256Hash(getEcrRefNumber(req) + terminalID);
				String responsePurchase = ecrCore.doCOMTransaction(port, baudRate, parity, dataBits, stopBits, reqData,
						TransactionType.PURCHASE.getTransactionCode(), signature);
				transactionData.setTransaction(responsePurchase);
				break;

			case REGISTER:
				req.setDate(paymentRequest.getDate());
				req.setCashRegisterNo(paymentRequest.getCashRegisterNo());
				reqData = req.getDate() + ";" + req.getCashRegisterNo() + "!";
				String responseRegister = ecrCore.doCOMTransaction(port, baudRate, parity, dataBits, stopBits, reqData,
						TransactionType.REGISTER.getTransactionCode(), signature);
				terminalID = getTerminalId(responseRegister);
				transactionData.setTransaction(responseRegister);
				break;
			case START_SESSION:
				req.setDate(paymentRequest.getDate());
				req.setCashRegisterNo(paymentRequest.getCashRegisterNo());
				reqData = req.getDate() + ";" + req.getCashRegisterNo() + "!";
				String responseStartSession = ecrCore.doCOMTransaction(port, baudRate, parity, dataBits, stopBits,
						reqData, TransactionType.START_SESSION.getTransactionCode(), signature);
				transactionData.setTransaction(responseStartSession);
				break;
			case END_SESSION:
				req.setDate(paymentRequest.getDate());
				req.setCashRegisterNo(paymentRequest.getCashRegisterNo());
				reqData = req.getDate() + ";" + req.getCashRegisterNo() + "!";
				String responseEndSession = ecrCore.doCOMTransaction(port, baudRate, parity, dataBits, stopBits,
						reqData, TransactionType.END_SESSION.getTransactionCode(), signature);
				transactionData.setTransaction(responseEndSession);
				break;
			case CHECK_STATUS:
				req.setDate(paymentRequest.getDate());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getEcrReferenceNo() + "!";
				signature = ecrCore.computeSha256Hash(getEcrRefNumber(req) + terminalID);
				String responseCheckStatus = ecrCore.doCOMTransaction(port, baudRate, parity, dataBits, stopBits,
						reqData, TransactionType.CHECK_STATUS.getTransactionCode(), signature);
				transactionData.setTransaction(responseCheckStatus);
				break;
			default:
				req.setDate(paymentRequest.getDate());
				req.setEcrReferenceNo(paymentRequest.getEcrReferenceNo());
				reqData = req.getDate() + ";" + req.getEcrReferenceNo() + "!";
				String response = ecrCore.doCOMTransaction(port, baudRate, parity, dataBits, stopBits, reqData, 0,
						signature);
				transactionData.setTransaction(response);
				break;
			}
		} catch (Exception e) {
			PaymentValidationException exception = new PaymentValidationException(e);
			exception.setErrorMessage("Enable to process the request" + e.getMessage());
			exception.setErrorCode(StatusCodes.ERROR_PROCESSING_TRANSACTION.getCode());
			logger.error("Enable to process the request", className, e);
			throw exception;
		}

		return transactionData;

	}
}
