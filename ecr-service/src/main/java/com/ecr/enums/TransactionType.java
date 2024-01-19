package com.ecr.enums;



public enum TransactionType {
	

	    PURCHASE("Purchase",0),
	    UPI("Upi",1),
	    BRAND_EMI("Brand Emi",2),
	    AUTHORIZATION("Authorization",3),
	    PURCHASE_ADVICE_FULL("Purchase Advice(Full Capture)",4),
	    AUTHORIZATION_EXTENSION("Authorization Extension",5),
	    AUTHORIZATION_VOID("Authorization Void",6),
	    ADVICE("Advice",7),
	    CASH_ADVANCE("Cash Advance",8),
	    REVERSAL("Reversal",9),
	    RECONCILIATION("Reconciliation",10),
	    FULL_DOWNLOAD("Full Download",11),
	    SET_SETTINGS("SET Settings",12),
	    GET_SETTINGS("GET Settings",13),
	    SET_TERMINAL_LANGUAGE("Set Terminal Language",14),
	    TERMINAL_STATUS("Terminal Status",15),
	    PREVIOUS_TRANSACTION_DETAILS("Previous Transaction Details",16),
	    REGISTER("Register",17),
	    START_SESSION("Start Session",18),
	    END_SESSION("End Session",19),
	    BILL_PAYMENT("Bill Payment",20),
	    RUNNING_TOTAL("Running Total",21),
	    PRINT_SUMMARY_REPORT("Print Summary Report",22),
	    DUPLICATE("Duplicate",23),
	    CHECK_STATUS("Check Status",24),
	    PARTIAL_DOWNLOAD("Partial Download",25),
	    SNAPSHOT_TOTAL("Snapshot Total",26);

	   
	    public String getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}

		public int getTransactionCode() {
			return transactionCode;
		}

		public void setTransactionCode(int transactionCode) {
			this.transactionCode = transactionCode;
		}

		private String transactionType;
	    
	    
	    private int transactionCode;

	    TransactionType(String transactionType, int transactionCode) {
	        this.transactionType = transactionType;
	        this.transactionCode = transactionCode;
	    }
	    
	}


