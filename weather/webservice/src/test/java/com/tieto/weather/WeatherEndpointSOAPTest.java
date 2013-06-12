package com.tieto.weather;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import com.tieto.weather.schema.CityWeatherType;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.schema.WeatherResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	{
		"/spring-ws-servlet-SOAP.xml", "/spring-weather-web-service.xml"
	}
) 
public class WeatherEndpointSOAPTest {
	
	@Autowired
    private ApplicationContext applicationContext;
    private MockWebServiceClient mockClient;
    @Autowired
    private ObjectFactory factory;

	@After
	public void tearDown() throws Exception {
	}
	
    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
	public void testHandleWeatherRequest() throws Exception {
		WeatherRequest request = factory.createWeatherRequest();
		request.getCity().add("Ostrava");
		 
		WeatherResponse response = factory.createWeatherResponse();
		CityWeatherType cityWeather = factory.createCityWeatherType();
		cityWeather.setLocation("Ostrava");
		cityWeather.setRelativeHumidity("40%");
		cityWeather.setTempC(BigDecimal.valueOf(21.0));
		cityWeather.setWeather("Clear");
		cityWeather.setWindDir("NNW");
		cityWeather.setWindString("Calm");
		response.getCityWeather().add(cityWeather);
		
		JAXBSource requestSource = new JAXBSource(JAXBContext.newInstance(request.getClass()), request);
		JAXBSource responseSource = new JAXBSource(JAXBContext.newInstance(response.getClass()), response);

	    mockClient.sendRequest(withPayload(requestSource)).andExpect(payload(responseSource));           
	}
	
	@Test
	public void testHandleWeatherAllCitiesRequest() throws Exception {
		WeatherRequest request = factory.createWeatherRequest();
		 
		WeatherResponse response = factory.createWeatherResponse();
		CityWeatherType cityWeather = factory.createCityWeatherType();
		cityWeather.setLocation("none");
		cityWeather.setRelativeHumidity("40%");
		cityWeather.setTempC(BigDecimal.valueOf(21.0));
		cityWeather.setWeather("Clear");
		cityWeather.setWindDir("NNW");
		cityWeather.setWindString("Calm");
		response.getCityWeather().add(cityWeather);
		
		JAXBSource requestSource = new JAXBSource(JAXBContext.newInstance(request.getClass()), request);
		JAXBSource responseSource = new JAXBSource(JAXBContext.newInstance(response.getClass()), response);

	    mockClient.sendRequest(withPayload(requestSource)).andExpect(payload(responseSource));           
	}
}
