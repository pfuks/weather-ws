package com.tieto.weather.service;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.vo.CityWeatherVO;

public interface WeatherService {

	public CityWeatherVO getWeatherData(String city) throws ServerError;
	
	public CityWeatherVO updateWeatherData(String city) throws ServerError;
}
