package com.ecr.wrapper;

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

public class TransactionRequest {

    private PaymentRequest paymentRequest;
    private TCPIPCOMData tcpipcomData;

    public PaymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public TCPIPCOMData getTcpipcomData() {
        return tcpipcomData;
    }

    public void setTcpipcomData(TCPIPCOMData tcpipcomData) {
        this.tcpipcomData = tcpipcomData;
    }
}