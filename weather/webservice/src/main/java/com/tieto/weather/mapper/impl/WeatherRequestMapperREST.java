package com.tieto.weather.mapper.impl;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.vo.WeatherRequestVO;

public class WeatherRequestMapperREST implements Mapper<String,WeatherRequestVO> {
	
	public WeatherRequestVO map(String source, WeatherRequestVO response) {
		
		if(source != null) {
			response.getCities().add(source);
		}
		
		return response; 
	}
}
