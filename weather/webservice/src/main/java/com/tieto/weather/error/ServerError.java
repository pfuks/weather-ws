package com.tieto.weather.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Server ERROR !!")
public class ServerError extends Exception {

	private static final long serialVersionUID = -6651212964001934558L;

	public ServerError() {
		
		super("Server ERROR !!");
	}
}