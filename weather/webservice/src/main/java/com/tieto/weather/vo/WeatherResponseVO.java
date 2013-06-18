package com.tieto.weather.vo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Value object for storing response.
 */
public class WeatherResponseVO {

	private Collection<CityWeatherVO> cityWeather;
	
	public WeatherResponseVO() {
		this.cityWeather = new ArrayList<CityWeatherVO>();
	}
	
	public WeatherResponseVO(CityWeatherVO city) {
		this.cityWeather = new ArrayList<CityWeatherVO>();
		this.cityWeather.add(city);
	}

	public Collection<CityWeatherVO> getCityWeather() {
		return cityWeather;
	}
	
}
