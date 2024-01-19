package com.ecr.validation;

import org.springframework.stereotype.Component;

import com.ecr.enums.StatusCodes;
import com.ecr.exception.PaymentValidationException;
import com.ecr.model.PaymentRequest;

@Component
public class PaymentRequestValidator {

	public void validatePaymentRequest(PaymentRequest paymentRequest) throws PaymentValidationException {

		if (paymentRequest.getDate() == null && paymentRequest.getEcrReferenceNo() == null) {
			throw new PaymentValidationException("PayLoad is not present", StatusCodes.ERROR_INALID_DATA.getCode());
		}

	}
}
