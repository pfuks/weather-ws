package com.tieto.weather.mapper.impl;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.vo.WeatherResponseVO;
import com.tieto.weather.wunderground.schema.Response;

public class WundergroundResponseMapper implements Mapper<Response, WeatherResponseVO> {

	public WeatherResponseVO map(Response source, WeatherResponseVO target) {
		
		CityWeatherVO city = new CityWeatherVO(); 
		city.setLocation(source.getCurrentObservation().getDisplayLocation().getFull());
		city.setRelativeHumidity(source.getCurrentObservation().getRelativeHumidity());
		city.setTemperatureCelsius(Double.valueOf(source.getCurrentObservation().getTempC()));
		city.setWeather(source.getCurrentObservation().getWeather());
		city.setWindDirection(source.getCurrentObservation().getWindDir());
		city.setWindString(source.getCurrentObservation().getWindString());
		
		target.getCityWeather().add(city);
		
		return target;
	}

}
