package com.tieto.weather.impl;

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
		
		if(weatherRequest.getCity().isEmpty()) {

			for (String city : cities.getCities().keySet()) {
				response.getCityWeather().add(service.getWeatherData(city));			
			}
		} else {
			for (String city : weatherRequest.getCity()) {
				response.getCityWeather().add(service.getWeatherData(city));				
			}
		}
		
		return responseMapper.map(response, factory.createWeatherResponse()); 
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
