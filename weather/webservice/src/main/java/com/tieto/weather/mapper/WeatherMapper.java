package com.tieto.weather.mapper;

import com.tieto.weather.schema.WeatherRequestType;
import com.tieto.weather.schema.WeatherResponseType;
import com.tieto.weather.vo.WeatherRequestVO;
import com.tieto.weather.vo.WeatherResponseVO;

public interface WeatherMapper {
	public WeatherRequestVO mapRequest(WeatherRequestType weatherRequest);

	public WeatherResponseType mapResponse(WeatherResponseVO weatherResponse);

}
