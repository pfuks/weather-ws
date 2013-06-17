package com.tieto.weather.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="lkjlkjkl")
public class ClientError extends Exception {

	public ClientError() {
		// TODO change to something better text
		super("Client request is wrong.");
	}
}