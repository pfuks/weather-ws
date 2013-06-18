package com.tieto.weather.error;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Error class for errors caused by client like bad request. 
 */
@SoapFault(faultCode = FaultCode.CLIENT)
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Client ERROR !!")
public class ClientError extends Exception {

	private static final long serialVersionUID = -6651212964001934558L;

	public ClientError() {
		
		super("Client ERROR !!");
		LoggerFactory.getLogger(ClientError.class).error("Client ERROR !!");
	}

	public ClientError(String errorMessage) {
		
		super(errorMessage);
		LoggerFactory.getLogger(ClientError.class).error(errorMessage);
	}
}