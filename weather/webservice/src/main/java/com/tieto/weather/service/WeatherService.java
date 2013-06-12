package com.tieto.weather.service;

import com.tieto.weather.vo.WeatherRequestVO;
import com.tieto.weather.vo.WeatherResponseVO;

public interface WeatherService {

	public WeatherResponseVO getWeatherData(WeatherRequestVO request) throws Exception;
}
