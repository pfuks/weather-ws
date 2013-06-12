package com.tieto.weather.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Unmarshaller;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.WeatherRequestVO;
import com.tieto.weather.vo.WeatherResponseVO;
import com.tieto.weather.wunderground.schema.Response;

public class WeatherServiceDBcache implements WeatherService {

	private Unmarshaller unmarshaller;
	private Mapper<Response, WeatherResponseVO> mapper;

	public WeatherResponseVO getWeatherData(WeatherRequestVO request) throws Exception {
/*
		WeatherResponseVO response = new WeatherResponseVO();
		// Response wundergroundResponse = factory.createResponse();

		CityWeatherVO cityWeather = new CityWeatherVO();
		ArrayList<String> arrayList = (ArrayList<String>) request.getCities();
		cityWeather.setLocation((arrayList.isEmpty() ? "none" : arrayList
				.get(0)));
		cityWeather.setRelativeHumidity("40%");
		cityWeather.setTemperatureCelsius(21.0);
		cityWeather.setWeather("Clear");
		cityWeather.setWindDirection("NNW");
		cityWeather.setWindString("Calm");
		response.getCityWeather().add(cityWeather);
*/
		String urlString = "http://api.wunderground.com/api/23a8ee338cc21fca/geolookup/conditions/forecast/q/czech/bohumin.xml";
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		InputStream inputStream = conn.getInputStream();
		
		Response wundergroundResponse = (Response) this.unmarshaller
				.unmarshal(new StreamSource(inputStream));
		
		
		return mapper.map(wundergroundResponse, new WeatherResponseVO());

	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	public void setWundergroundResponseMapper(Mapper<Response, WeatherResponseVO> mapper) {
		this.mapper = mapper;
	}
}
