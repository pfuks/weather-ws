package com.tieto.weather.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.mapper.impl.WundergroundResponseMapper;
import com.tieto.weather.service.WeatherServiceCached;
import com.tieto.weather.vo.CitiesVO;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.wunderground.schema.Response;

/**
 * Class implementation used for fetching weather data.
 * Using Spring cache functionality.
 */
public class WeatherServiceCachedSimple implements WeatherServiceCached {

	private WundergroundResponseMapper mapper;
	private String apikey;
	private String urlString;
	private CitiesVO cities;
	private RestTemplate restTemplate;

	@Cacheable(value = "weatherCache")
	public CityWeatherVO getWeatherData(String city) throws ServerError {
		
		LoggerFactory.getLogger(WeatherServiceCached.class).info("Not found in cache -> call Wunderground: " + city);
		
		return getCityWeather(city);
	}
	
	@CachePut(value = "weatherCache")
	public CityWeatherVO updateWeatherData(String city) throws ServerError {
		
		CityWeatherVO result;
		result = getCityWeather(city);
	    
		LoggerFactory.getLogger(WeatherServiceCached.class).info("Put to cache: " + city);
	    
		return result;

	}
	
	/**
	 * Method used for connecting to wunderground and returning weather data.
	 * 
	 * @param city City for request.
	 * @return Response from wunderground.
	 * @throws ServerError
	 */
	private CityWeatherVO getCityWeather(String city) throws ServerError {

		Response wundergroundResponse = restTemplate.getForObject(urlString, Response.class, apikey, cities.getCities().get(city), city);
		LoggerFactory.getLogger(WeatherServiceCached.class).info("Call Wunderground for: " + city);
		
		return mapper.mapWundergroundResponse(wundergroundResponse, new CityWeatherVO());
	}
	
	public void setWundergroundResponseMapper(WundergroundResponseMapper mapper) {
		this.mapper = mapper;
	}

	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
	
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
}
