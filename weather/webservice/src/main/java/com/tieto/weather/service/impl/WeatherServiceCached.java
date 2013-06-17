package com.tieto.weather.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.stream.StreamSource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.oxm.Unmarshaller;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVO;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.wunderground.schema.Response;

public class WeatherServiceCached implements WeatherService {

	private Unmarshaller unmarshaller;
	private Mapper<Response, CityWeatherVO> mapper;
	private String apiKey;
	private String url;
	private CitiesVO cities;
	private RestTemplate restTemplate;

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
		
				
		String configuredURL = String.format(url, apiKey, cities.getCities().get(city), city);
		
		URL url = new URL(configuredURL);//TODO URI
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

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
	
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
