package com.tieto.weather.mapper.impl;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.wunderground.schema.Response;

public class WundergroundResponseMapper implements Mapper<Response, CityWeatherVO> {

	public CityWeatherVO map(Response source, CityWeatherVO target) {
		
		target.setLocation(source.getCurrentObservation().getDisplayLocation().getFull());
		target.setRelativeHumidity(source.getCurrentObservation().getRelativeHumidity());
		target.setTemperatureCelsius(Double.valueOf(source.getCurrentObservation().getTempC()));
		target.setWeather(source.getCurrentObservation().getWeather());
		target.setWindDirection(source.getCurrentObservation().getWindDir());
		target.setWindString(source.getCurrentObservation().getWindString());
				
		return target;
	}

}
