package com.tieto.weather.service;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.vo.CityWeatherVO;

/**
 * Service used for fetching weather data.
 */
public interface WeatherService {

	/**
	 * Method used for getting weather data.
	 * 
	 * @param city City for weather data fetch.
	 * @return Weather data for city from request.
	 * @throws ServerError
	 */
	public CityWeatherVO getWeatherData(String city) throws ServerError;
}
