package com.tieto.weather.mapper.impl;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.vo.WeatherRequestVO;

public class WeatherRequestMapperSOAP implements Mapper<WeatherRequest,WeatherRequestVO> {
	
	public WeatherRequestVO map(WeatherRequest source, WeatherRequestVO response) {

		response.getCities().addAll(source.getCity());
						
		return response; 
	}
}
