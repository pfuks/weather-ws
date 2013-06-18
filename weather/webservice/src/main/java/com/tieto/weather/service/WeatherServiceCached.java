package com.tieto.weather.service;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.vo.CityWeatherVO;

/**
 * Service used for fetching weather data.
 * Contains cache functionality.
 */
public interface WeatherServiceCached extends WeatherService {

	/**
	 * Method used for updating data to cache.
	 * 
	 * @param city City for weather data refetch.
	 * @return Weather data for city from request. This value is stored into cache.
	 * @throws ServerError
	 */
	public CityWeatherVO updateWeatherData(String city) throws ServerError;
}
