package com.ecr.model;

import com.ecr.enums.TransactionType;

public class PaymentRequest {
	private String amount;
	private String date;
	private String cashBackAmount;
	private String preAuthAMount;
	private String print;
	private String ecrReferenceNo;
	private String refundAmount;
	private String origTransactionDate;
	private String origApproveCode;
	private String capture;
	private String origTransactionAmt;
	private String cashAdvanceAmt;
	private String vendorId;
	private String vendorTerminalType;
	private String trsmId;
	private String vendorKeyIndex;
	private String samaKeyIndex;
	private String terminalLanguage;
	private String billPayAmt;
	private String billerId;
	private String billerNumber;
	private String cashRegisterNo;
	private String prevEcrNo;
	private String rrnNumber;
	private String originalRefundDate;
	private String authAmount;
	private String referenceNumber;
	private TransactionType txnType;

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String getPrevEcrNo() {
		return prevEcrNo;
	}

	public void setPrevEcrNo(String prevEcrNo) {
		this.prevEcrNo = prevEcrNo;
	}

	public String getCashRegisterNo() {
		return cashRegisterNo;
	}

	public void setCashRegisterNo(String cashRegisterNo) {
		this.cashRegisterNo = cashRegisterNo;
	}

	public String getBillPayAmt() {
		return billPayAmt;
	}

	public void setBillPayAmt(String billPayAmt) {
		this.billPayAmt = billPayAmt;
	}

	public String getBillerId() {
		return billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public String getBillerNumber() {
		return billerNumber;
	}

	public void setBillerNumber(String billerNumber) {
		this.billerNumber = billerNumber;
	}

	public String getTerminalLanguage() {
		return terminalLanguage;
	}

	public void setTerminalLanguage(String terminalLanguage) {
		this.terminalLanguage = terminalLanguage;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorTerminalType() {
		return vendorTerminalType;
	}

	public void setVendorTerminalType(String vendorTerminalType) {
		this.vendorTerminalType = vendorTerminalType;
	}

	public String getTrsmId() {
		return trsmId;
	}

	public void setTrsmId(String trsmId) {
		this.trsmId = trsmId;
	}

	public String getVendorKeyIndex() {
		return vendorKeyIndex;
	}

	public void setVendorKeyIndex(String vendorKeyIndex) {
		this.vendorKeyIndex = vendorKeyIndex;
	}

	public String getSamaKeyIndex() {
		return samaKeyIndex;
	}

	public void setSamaKeyIndex(String samaKeyIndex) {
		this.samaKeyIndex = samaKeyIndex;
	}

	public String getCashAdvanceAmt() {
		return cashAdvanceAmt;
	}

	public void setCashAdvanceAmt(String cashAdvanceAmt) {
		this.cashAdvanceAmt = cashAdvanceAmt;
	}

	public String getOrigTransactionAmt() {
		return origTransactionAmt;
	}

	public void setOrigTransactionAmt(String origTransactionAmt) {
		this.origTransactionAmt = origTransactionAmt;
	}

	public String getOrigTransactionDate() {
		return origTransactionDate;
	}

	public void setOrigTransactionDate(String origTransactionDate) {
		this.origTransactionDate = origTransactionDate;
	}

	public String getOrigApproveCode() {
		return origApproveCode;
	}

	public void setOrigApproveCode(String origApproveCode) {
		this.origApproveCode = origApproveCode;
	}

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}

	public String getAuthAmount() {
		return authAmount;
	}

	public void setAuthAmount(String authAmount) {
		this.authAmount = authAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRrnNumber() {
		return rrnNumber;
	}

	public void setRrnNumber(String rrnNumber) {
		this.rrnNumber = rrnNumber;
	}

	public String getOriginalRefundDate() {
		return originalRefundDate;
	}

	public void setOriginalRefundDate(String originalRefundDate) {
		this.originalRefundDate = originalRefundDate;
	}

	public String getEcrReferenceNo() {
		return ecrReferenceNo;
	}

	public void setEcrReferenceNo(String ecrReferenceNo) {
		this.ecrReferenceNo = ecrReferenceNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCashBackAmount() {
		return cashBackAmount;
	}

	public void setCashBackAmount(String cashBackAmount) {
		this.cashBackAmount = cashBackAmount;
	}

	public String getPreAuthAMount() {
		return preAuthAMount;
	}

	public void setPreAuthAMount(String preAuthAMount) {
		this.preAuthAMount = preAuthAMount;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}
}
