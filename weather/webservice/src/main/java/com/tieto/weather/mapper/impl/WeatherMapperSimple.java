package com.tieto.weather.mapper.impl;

import java.math.BigDecimal;

import com.tieto.weather.mapper.WeatherMapper;
import com.tieto.weather.schema.CityWeatherType;
import com.tieto.weather.schema.WeatherRequestType;
import com.tieto.weather.schema.WeatherResponseType;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.vo.WeatherRequestVO;
import com.tieto.weather.vo.WeatherResponseVO;

public class WeatherMapperSimple implements WeatherMapper {
	
	public WeatherRequestVO mapRequest(WeatherRequestType weatherRequest) {
		
		WeatherRequestVO result = new WeatherRequestVO();		
		result.getCities().addAll(weatherRequest.getCity());
						
		return result; 
	}

	
	public WeatherResponseType mapResponse(WeatherResponseVO weatherResponse) {
		
		WeatherResponseType result = new WeatherResponseType();	
		for (CityWeatherVO weather : weatherResponse.getCityWeather()) {
			CityWeatherType item = new CityWeatherType();
			item.setLocation(weather.getLocation());
			item.setRelativeHumidity(weather.getRelativeHumidity());
			item.setTempC(BigDecimal.valueOf(weather.getTemperatureCelsius()));
			item.setWeather(weather.getWeather());
			item.setWindDir(weather.getWindDirection());
			item.setWindString(weather.getWindString());
			result.getCityWeather().add(item);
		}
		
		return result; 
	}
}
