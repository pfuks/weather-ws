package com.tieto.weather.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.tieto.weather.vo.CityWeatherVO;

public class WeatherServiceCachedStub extends WeatherServiceCached {

	@Cacheable(value = "weatherCache")
	public CityWeatherVO getWeatherData(String city) throws Exception {
		
		System.out.println("Not found in cache: "+city);
		return getCityWeather(city);
	}
	
	@CachePut(value = "weatherCache")
	public CityWeatherVO updateWeatherData(String city) throws Exception {
		
		System.out.println("Put to cache: "+city);
		return getCityWeather(city);

	}
	
	private CityWeatherVO getCityWeather(String city) throws Exception {
				
		return new CityWeatherVO();
	}
}
