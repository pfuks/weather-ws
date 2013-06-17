package com.tieto.weather;

import com.tieto.weather.error.ClientError;
import com.tieto.weather.error.ServerError;
import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.schema.WeatherResponse;

public interface WeatherEndpoint {

	/**
	 * Endpoint method for getting weather data.
	 * 
	 * @param weatherRequest Contains list of cities.
	 * @return Weather data for cities from request.
	 * @throws ClientError 
	 * @throws ServerError 
	 */
	public WeatherResponse handleWeatherRequest(WeatherRequest weatherRequest) throws ServerError;
}
