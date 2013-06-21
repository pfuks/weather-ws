package com.tieto.weather.mapper.impl;

import java.text.ParseException;

import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.wunderground.schema.Response;

/**
 * Class used for mapping wunderground response to application VO.
 */
public class WundergroundResponseMapper {

	public CityWeatherVO mapWundergroundResponse(Response source, CityWeatherVO target) throws ParseException {
		
		target.setLocation(source.getCurrentObservation().getDisplayLocation().getFull());
		target.setRelativeHumidity(source.getCurrentObservation().getRelativeHumidity());
		target.setTemperatureCelsius(Double.valueOf(source.getCurrentObservation().getTempC()));
		target.setWeather(source.getCurrentObservation().getWeather());
		target.setWindDirection(source.getCurrentObservation().getWindDir());
		target.setWindString(source.getCurrentObservation().getWindString());
		target.setWeatherDate(source.getCurrentObservation().getObservationTime());
		
		return target;
	}

}
