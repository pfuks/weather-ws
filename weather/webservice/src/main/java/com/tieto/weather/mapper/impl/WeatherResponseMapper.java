package com.tieto.weather.mapper.impl;

import java.math.BigDecimal;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.schema.CityWeatherType;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.vo.WeatherResponseVO;

public class WeatherResponseMapper implements Mapper<WeatherResponseVO,WeatherResponse> {
	
	public WeatherResponse map(WeatherResponseVO source, WeatherResponse response) {
		
		for (CityWeatherVO weather : source.getCityWeather()) {
			CityWeatherType item = new CityWeatherType();
			item.setLocation(weather.getLocation());
			item.setRelativeHumidity(weather.getRelativeHumidity());
			item.setTempC(BigDecimal.valueOf(weather.getTemperatureCelsius()));
			item.setWeather(weather.getWeather());
			item.setWindDir(weather.getWindDirection());
			item.setWindString(weather.getWindString());
			response.getCityWeather().add(item);
		}
		
		return response; 
	}
}
