package com.tieto.weather.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVOFactory;
import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.wunderground.schema.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath:/weather-web-service-test.xml"})
public class WeatherServiceCachedSimpleTest {
	
	@Autowired
    private WeatherService service;
    @Autowired
    private CitiesVOFactory cities;
    @Autowired
	private RestTemplate restTemplate;
    private String URI;
    private String apikey;

    @Before
    public void setUp() throws ServerError {
    	
    	HashMap<String, String> citiesMap = new HashMap<String, String>();
    	citiesMap.put("Bohumin", "Czech");
    	citiesMap.put("Oslo", "Norway");
    	
    	URI = "http://api.wunderground.com/api/{apiKey}/conditions/q/{country}/{city}.xml";
    	apikey = "23a8ee338cc21fca";
    	
    	Response response = mock(Response.class, RETURNS_DEEP_STUBS);
    	
    	when(response.getCurrentObservation().getDisplayLocation().getFull()).thenReturn("Bohumin");
    	when(response.getCurrentObservation().getRelativeHumidity()).thenReturn("40%");
    	when(response.getCurrentObservation().getTempC()).thenReturn("21");
    	when(response.getCurrentObservation().getWeather()).thenReturn("Clear");
    	when(response.getCurrentObservation().getWindDir()).thenReturn("NNW");
    	when(response.getCurrentObservation().getWindString()).thenReturn("Calm");
    	
    	when(restTemplate.getForObject(URI, Response.class, apikey, "Czech", "Bohumin")).thenReturn(response);
		when(cities.getCities()).thenReturn(citiesMap);
    }
    
    @Test
	public void testGetCityWeather() throws ServerError {
    	
    	String city = "Bohumin";
    	CityWeatherVO weatherData = service.getWeatherData(city);
    	
    	assertEquals("Bohumin", weatherData.getLocation());
    	assertEquals("40%", weatherData.getRelativeHumidity());
    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
    	assertEquals("Clear", weatherData.getWeather());
    	assertEquals("NNW", weatherData.getWindDirection());
    	assertEquals("Calm", weatherData.getWindString());
    }
    
    @Test(expected = ServerError.class)
    @DirtiesContext
   	public void testHandleWeatherRequestWrongCity() throws ServerError {
    	
    	when(restTemplate.getForObject(URI, Response.class, apikey, "Norway", "Oslo")).thenThrow(new RestClientException("REST exception"));
    	
    	String city = "Oslo";
    	service.getWeatherData(city);
       }

}
