package com.tieto.weather.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tieto.weather.error.ClientError;
import com.tieto.weather.error.ServerError;
import com.tieto.weather.scheduler.WeatherScheduler;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVO;
import com.tieto.weather.vo.CityWeatherVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class,
locations = {"classpath:/spring-ws-servlet-REST.xml", "classpath:/weather-web-service.xml"})
public class WeatherRESTControllerTest {
	
	@Autowired
    private ObjectFactory factory;
    @ReplaceWithMock
    @Autowired
    private WeatherService service;
    @ReplaceWithMock
    @Autowired
    private CitiesVO cities;
    @Autowired
    private WeatherRESTController controller;
    @ReplaceWithMock
    private WeatherScheduler wundergroundScheduler;
    
    @Before
    public void setUp() throws ServerError {
    	
    	HashMap<String, String> citiesMap = new HashMap<String, String>();
    	citiesMap.put("Bohumin", "Czech");
    	citiesMap.put("Oslo", "Norway");
    	
    	CityWeatherVO cityWeatherBohumin = new CityWeatherVO();
    	cityWeatherBohumin.setLocation("Bohumin");
    	cityWeatherBohumin.setRelativeHumidity("40%")	;	
    	cityWeatherBohumin.setTemperatureCelsius(21.0);
    	cityWeatherBohumin.setWeather("Clear");
    	cityWeatherBohumin.setWindDirection("NNW");
    	cityWeatherBohumin.setWindString("Calm");
    	
    	CityWeatherVO cityWeatherOslo = new CityWeatherVO();
    	cityWeatherOslo.setLocation("Oslo");
    	cityWeatherOslo.setRelativeHumidity("30%")	;	
    	cityWeatherOslo.setTemperatureCelsius(11.0);
    	cityWeatherOslo.setWeather("Cloudy");
    	cityWeatherOslo.setWindDirection("NNE");
    	cityWeatherOslo.setWindString("Strong");
    	
		when(cities.getCities()).thenReturn(citiesMap);
        when(service.getWeatherData("Bohumin")).thenReturn(cityWeatherBohumin);
        when(service.getWeatherData("Oslo")).thenReturn(cityWeatherOslo);
    }
    
    @Test
	public void testGetCityWeather() throws ServerError, ClientError {
    	String city = "Bohumin";
		
    	WeatherResponse response = controller.getCityWeather(city);
    	
    	assertEquals(1, response.getCityWeather().size());
    	assertEquals("Bohumin", response.getCityWeather().get(0).getLocation());
    	assertEquals("40%", response.getCityWeather().get(0).getRelativeHumidity());
    	assertEquals(BigDecimal.valueOf(21.0), response.getCityWeather().get(0).getTempC());
    	assertEquals("Clear", response.getCityWeather().get(0).getWeather());
    	assertEquals("NNW", response.getCityWeather().get(0).getWindDir());
    	assertEquals("Calm", response.getCityWeather().get(0).getWindString());
    }
    
    @Test(expected = ClientError.class)
	public void testHandleWeatherRequestWrongCity() throws ServerError, ClientError {
    	
    	String city = "Ostrava";
    	controller.getCityWeather(city);
    }
    
    @Test(expected = ServerError.class)
    @DirtiesContext
	public void testHandleWeatherRequestServerError() throws ServerError, ClientError {
    	
    	// call for server fault simulation
        when(service.getWeatherData("Oslo")).thenThrow(new ServerError());
        
        String city = "Oslo";
        
    	controller.getCityWeather(city);
    }
    
    @Test
	public void testGetAllWeathers() throws ServerError, ClientError {
    	
    	WeatherResponse response = controller.getAllWeathers();
    	
    	assertEquals(2, response.getCityWeather().size());
    	
    	assertEquals("Bohumin", response.getCityWeather().get(0).getLocation());
    	assertEquals("40%", response.getCityWeather().get(0).getRelativeHumidity());
    	assertEquals(BigDecimal.valueOf(21.0), response.getCityWeather().get(0).getTempC());
    	assertEquals("Clear", response.getCityWeather().get(0).getWeather());
    	assertEquals("NNW", response.getCityWeather().get(0).getWindDir());
    	assertEquals("Calm", response.getCityWeather().get(0).getWindString());
    	
    	assertEquals("Oslo", response.getCityWeather().get(1).getLocation());
    	assertEquals("30%", response.getCityWeather().get(1).getRelativeHumidity());
    	assertEquals(BigDecimal.valueOf(11.0), response.getCityWeather().get(1).getTempC());
    	assertEquals("Cloudy", response.getCityWeather().get(1).getWeather());
    	assertEquals("NNE", response.getCityWeather().get(1).getWindDir());
    	assertEquals("Strong", response.getCityWeather().get(1).getWindString());
    }
}
