package com.tieto.weather.service.impl;


import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.vo.WeatherRequestVO;
import com.tieto.weather.vo.WeatherResponseVO;
 
public class WeatherServiceDBcache implements WeatherService{
                                                                              
		@Override
		public WeatherResponseVO getWeatherData(WeatherRequestVO request) {
			
			WeatherResponseVO response = new WeatherResponseVO();
			
			CityWeatherVO cityWeather = new CityWeatherVO();
			cityWeather.setLocation("Ostrava");
			cityWeather.setRelativeHumidity("40%");
			cityWeather.setTemperatureCelsius(21.0);
			cityWeather.setWeather("Clear");
			cityWeather.setWindDirection("NNW");
			cityWeather.setWindString("Calm");
			response.getCityWeather().add(cityWeather);
			
			return response; 
			
		}
}
