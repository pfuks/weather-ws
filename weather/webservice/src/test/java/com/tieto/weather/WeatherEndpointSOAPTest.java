package com.tieto.weather;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

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

	@After
	public void tearDown() throws Exception {
	}
	
    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

	@Test
	public void testHandleWeatherRequest() {
		 Source requestPayload = new StringSource("<sch:WeatherRequest xmlns:sch='http://weather.tieto.com/schemas'>" +
     	        "<sch:City>Ostrava</sch:City>" +
     	      "</sch:WeatherRequest>");
     
		 Source responsePayload = new StringSource(
     	      "<ns2:WeatherResponse xmlns:ns2='http://weather.tieto.com/schemas'>" +
     	        "<ns2:CityWeather>" +
     	           "<ns2:location>Ostrava</ns2:location>" +
     	           "<ns2:temp_c>21.0</ns2:temp_c>" +
     	           "<ns2:relative_humidity>40%</ns2:relative_humidity>" +
     	           "<ns2:wind_dir>NNW</ns2:wind_dir>" +
     	           "<ns2:weather>Clear</ns2:weather>" +
     	           "<ns2:wind_string>Calm</ns2:wind_string>" +
     	        "</ns2:CityWeather>" +
     	      "</ns2:WeatherResponse>");

	    mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));           
	}
   
}
