package com.tieto.weather.vo;

import java.util.ArrayList;
import java.util.Collection;

public class WeatherResponseVO {

	private Collection<CityWeatherVO> cityWeather;
	
	public WeatherResponseVO() {
		this.cityWeather = new ArrayList<CityWeatherVO>();
	}

	public Collection<CityWeatherVO> getCityWeather() {
		return cityWeather;
	}
	
}
