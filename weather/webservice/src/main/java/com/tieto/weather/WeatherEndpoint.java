package com.tieto.weather;

import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.schema.WeatherResponse;

public interface WeatherEndpoint {

	public WeatherResponse handleWeatherRequest(WeatherRequest weatherRequest) throws Exception;
}
