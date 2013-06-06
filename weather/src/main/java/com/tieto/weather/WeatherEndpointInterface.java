package com.tieto.weather;

import javax.xml.bind.JAXBElement;
import com.tieto.weather.schema.WeatherRequestType;
import com.tieto.weather.schema.WeatherResponseType;

public interface WeatherEndpointInterface {

	public JAXBElement<WeatherResponseType> handleWeatherRequest(JAXBElement<WeatherRequestType> weatherRequest) throws Exception;
}
