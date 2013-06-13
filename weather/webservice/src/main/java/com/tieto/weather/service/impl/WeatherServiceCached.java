package com.tieto.weather.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.stream.StreamSource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.oxm.Unmarshaller;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.wunderground.schema.Response;

public class WeatherServiceCached implements WeatherService {

	private Unmarshaller unmarshaller;
	private Mapper<Response, CityWeatherVO> mapper;

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
				
		String urlString = "http://api.wunderground.com/api/23a8ee338cc21fca/geolookup/conditions/forecast/q/czech/bohumin.xml";
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		InputStream inputStream = conn.getInputStream();
		
		Response wundergroundResponse = (Response) this.unmarshaller
				.unmarshal(new StreamSource(inputStream));
		System.out.println("Call Wunderground for: "+city);		
		return mapper.map(wundergroundResponse, new CityWeatherVO());
	}
		
	
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	public void setWundergroundResponseMapper(Mapper<Response, CityWeatherVO> mapper) {
		this.mapper = mapper;
	}
}
