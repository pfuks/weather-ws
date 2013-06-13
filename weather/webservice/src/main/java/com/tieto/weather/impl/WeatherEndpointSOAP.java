package com.tieto.weather.impl;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tieto.weather.WeatherEndpoint;
import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.WeatherRequestVO;
import com.tieto.weather.vo.WeatherResponseVO;


@Endpoint
public class WeatherEndpointSOAP implements WeatherEndpoint{

	private static final String NAMESPACE_URI = "http://weather.tieto.com/schemas";
	
	private final ObjectFactory factory;
	private Mapper<WeatherRequest,WeatherRequestVO> requestMapper;
	private Mapper<WeatherResponseVO,WeatherResponse> responseMapper;
	private WeatherService service;
	
	public WeatherEndpointSOAP(ObjectFactory factory) {
		this.factory = factory;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "WeatherRequest")
	@ResponsePayload
	public WeatherResponse handleWeatherRequest(@RequestPayload WeatherRequest weatherRequest) {
		
		// TODO VO constructor here?
		WeatherRequestVO request = requestMapper.map(weatherRequest, new WeatherRequestVO());
		WeatherResponseVO response = null;
		try {
			response = service.getWeatherData2(request);
			response = service.getWeatherData(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseMapper.map(response, factory.createWeatherResponse()); 
	}
	
	public void setWeatherRequestMapper( Mapper<WeatherRequest,WeatherRequestVO> requestMapper) {
		this.requestMapper = requestMapper;
	}
	
	public void setWeatherResponseMapper( Mapper<WeatherResponseVO,WeatherResponse> responseMapper) {
		this.responseMapper = responseMapper;
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}

}
