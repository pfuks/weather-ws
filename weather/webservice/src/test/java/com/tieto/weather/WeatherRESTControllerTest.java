package com.tieto.weather;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tieto.weather.schema.CityWeatherType;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
	{
		"/spring-ws-servlet-REST.xml", "/spring-weather-web-service.xml"
	}
) 
public class WeatherRESTControllerTest {
	
	@Autowired
    private WebApplicationContext ctx;
	private MockMvc mockMvc;
	@Autowired
	ObjectFactory factory;
	@Autowired
	ObjectMapper mapper;

	@After
	public void tearDown() throws Exception {
	}
	
    @Before
    public void createClient() {
    	 this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    /*
    @Test
    public void testGetCityWeather() throws Exception {
    	
    	WeatherResponse response = factory.createWeatherResponse();
		CityWeatherType cityWeather = factory.createCityWeatherType();
		cityWeather.setLocation("Ostrava");
		cityWeather.setRelativeHumidity("40%");
		cityWeather.setTempC(BigDecimal.valueOf(21.0));
		cityWeather.setWeather("Clear");
		cityWeather.setWindDir("NNW");
		cityWeather.setWindString("Calm");
		response.getCityWeather().add(cityWeather);
		
		String responseString = mapper.writeValueAsString(response);
		
    	mockMvc.perform(get("/rest/{city}","Ostrava").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().string(responseString));
    	
    }
    
    @Test
    public void testGetAllWeathers() throws Exception {
    	
    	WeatherResponse response = factory.createWeatherResponse();
		CityWeatherType cityWeather = factory.createCityWeatherType();
		cityWeather.setLocation("none");
		cityWeather.setRelativeHumidity("40%");
		cityWeather.setTempC(BigDecimal.valueOf(21.0));
		cityWeather.setWeather("Clear");
		cityWeather.setWindDir("NNW");
		cityWeather.setWindString("Calm");
		response.getCityWeather().add(cityWeather);
		
		String responseString = mapper.writeValueAsString(response);
		
    	mockMvc.perform(get("/rest").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().string(responseString));
    	
    }
    */
}
