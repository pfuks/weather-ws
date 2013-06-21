package com.tieto.weather.error;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;


/**
 * Error class for errors caused by server like missing legacy data. 
 */
@SoapFault(faultCode = FaultCode.SERVER)
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Server ERROR !!")
public class ServerError extends Exception {

	private static final long serialVersionUID = -6651212964001934558L;

	public ServerError() {
		
		super("Server ERROR !!");
		LoggerFactory.getLogger(ServerError.class).error("Server ERROR !!");
	}
	
	public ServerError(String errorMessage) {
		
		super(errorMessage);
		LoggerFactory.getLogger(ServerError.class).error(errorMessage);
	}
	
	public ServerError(String errorMessage, Exception ex) {
		
		super(errorMessage);
		LoggerFactory.getLogger(ServerError.class).error(errorMessage + ex.getMessage());
	}
}