package com.tieto.weather;

import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CityWeatherVO;

/**
 * Mock for testing purposes.
 */

public class WeatherServiceCachedMock implements WeatherService{
	
	public CityWeatherVO getWeatherData(String city) {
		
		CityWeatherVO cityWeatherVO = new CityWeatherVO();
		cityWeatherVO.setLocation(city);
		cityWeatherVO.setRelativeHumidity("40%")	;	
		cityWeatherVO.setTemperatureCelsius(21.0);
		cityWeatherVO.setWeather("Clear");
		cityWeatherVO.setWindDirection("NNW");
		cityWeatherVO.setWindString("Calm");
				
		return cityWeatherVO;
	}
}
