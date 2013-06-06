package com.tieto.weather;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
import com.tieto.weather.schema.WeatherRequestType;
import com.tieto.weather.schema.WeatherResponseType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-ws-servlet.xml") 
public class WeatherEndpointTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHandleWeatherRequest() {
	/*	fail("Not yet implemented"); // TODO
*/	}

	
	@Autowired
    private ApplicationContext applicationContext;
    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testExecute() {
        WeatherRequestType request = new WeatherRequestType();
        request.getCity().add("Ostrava");
                
        
        WeatherResponseType expectedResponse = new WeatherResponseType();
        CityWeatherType e = new CityWeatherType();
        e.setLocation("Ostrava");
		expectedResponse.getCityWeather().add(e);
        
        
        execute(request, expectedResponse);
    }
    

    
    private <TReq, TResp> void execute(TReq request, TResp expectedResponse) {
        try {
            mockClient.sendRequest(withPayload(new JAXBSource(JAXBContext.newInstance(request.getClass()), request)))
                    .andExpect(payload(new JAXBSource(JAXBContext.newInstance(expectedResponse.getClass()), expectedResponse)));
        } catch (JAXBException ex) {
            throw new AssertionError(ex);
        }
    } 
	
	
}
