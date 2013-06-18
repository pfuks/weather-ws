package com.tieto.weather.impl;

import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tieto.weather.WeatherEndpoint;
import com.tieto.weather.error.ServerError;
import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVO;
import com.tieto.weather.vo.WeatherResponseVO;

/**
 * Endpoint for SOAP calls.
 */
@Endpoint
public class WeatherEndpointSOAP implements WeatherEndpoint{

	private static final String NAMESPACE_URI = "http://weather.tieto.com/schemas";
	
	private final ObjectFactory factory;
	private Mapper<WeatherResponseVO,WeatherResponse> responseMapper;
	private WeatherService service;
	private CitiesVO cities;
	
	public WeatherEndpointSOAP(ObjectFactory factory) {
		this.factory = factory;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "WeatherRequest")
	@ResponsePayload
	public WeatherResponse handleWeatherRequest(@RequestPayload WeatherRequest weatherRequest) throws ServerError {
		
		WeatherResponseVO response = new WeatherResponseVO();
		WeatherResponse result;
		
		// TODO check for valid city -> error
		if(weatherRequest.getCity().isEmpty()) {
			LoggerFactory.getLogger(WeatherEndpointSOAP.class).info("SOAP Request for all cities.");
			for (String city : cities.getCities().keySet()) {				
				response.getCityWeather().add(service.getWeatherData(city));			
			}
		} else {
			LoggerFactory.getLogger(WeatherEndpointSOAP.class).info("SOAP Request for city: " + weatherRequest.getCity());
			for (String city : weatherRequest.getCity()) {
				response.getCityWeather().add(service.getWeatherData(city));				
			}
		}
		
		result = responseMapper.map(response, factory.createWeatherResponse()); 
		
		LoggerFactory.getLogger(WeatherRESTController.class).info("SOAP Request completed.");
		
		return result;
	}
	
	public void setWeatherResponseMapper( Mapper<WeatherResponseVO,WeatherResponse> responseMapper) {
		this.responseMapper = responseMapper;
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}

}
