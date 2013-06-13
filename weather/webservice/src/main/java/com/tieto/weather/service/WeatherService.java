package com.tieto.weather.service;

import com.tieto.weather.vo.CityWeatherVO;

public interface WeatherService {

	public CityWeatherVO getWeatherData(String city) throws Exception;
	
	public CityWeatherVO updateWeatherData(String city) throws Exception;
}
