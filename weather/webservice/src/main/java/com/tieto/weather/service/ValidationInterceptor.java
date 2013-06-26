package com.tieto.weather.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class ValidationInterceptor implements EndpointInterceptor {

	public boolean handleRequest(MessageContext messageContext, Object endpoint)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handleResponse(MessageContext messageContext, Object endpoint)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handleFault(MessageContext messageContext, Object endpoint)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public void afterCompletion(MessageContext messageContext, Object endpoint,
			Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
