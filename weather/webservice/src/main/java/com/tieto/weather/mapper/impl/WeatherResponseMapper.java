package com.tieto.weather.mapper.impl;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.tieto.weather.schema.CityWeatherType;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.vo.WeatherResponseVO;

/**
 * Class used for mapping application VO to application response.
 */
public class WeatherResponseMapper {
	
	public WeatherResponse mapWeatherResponse(WeatherResponseVO source, WeatherResponse response) {
		
		for (CityWeatherVO weather : source.getCityWeather()) {
			CityWeatherType item = new CityWeatherType();
			item.setLocation(weather.getLocation());
			item.setRelativeHumidity(weather.getRelativeHumidity());
			item.setTempC(BigDecimal.valueOf(weather.getTemperatureCelsius()));
			item.setWeather(weather.getWeather());
			item.setWindDir(weather.getWindDirection());
			item.setWindString(weather.getWindString());
			
//			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(weather.getWeatherDate());
			item.setObservationTime(weather.getWeatherDate());
			response.getCityWeather().add(item);
		}
		
		return response; 
	}
}
