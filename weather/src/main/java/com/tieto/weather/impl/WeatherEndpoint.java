package com.tieto.weather.impl;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tieto.weather.WeatherEndpointInterface;
import com.tieto.weather.schema.CityWeatherType;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherRequestType;
import com.tieto.weather.schema.WeatherResponseType;

@Endpoint
public class WeatherEndpoint implements WeatherEndpointInterface{

	private static final String NAMESPACE_URI = "http://weather.tieto.com/schemas";
	
	private final ObjectFactory factory = new ObjectFactory();
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "WeatherRequest")
	@ResponsePayload
	public JAXBElement<WeatherResponseType> handleWeatherRequest(@RequestPayload JAXBElement<WeatherRequestType> weatherRequest) {

		WeatherResponseType response = new WeatherResponseType();
		
		CityWeatherType city = new CityWeatherType();
		city.setLocation("Ostrava");
		response.getCityWeather().add(city);
		
		return factory.createWeatherResponse(response);
	}

}
