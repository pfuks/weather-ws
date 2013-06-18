package com.tieto.weather.impl;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tieto.weather.error.ClientError;
import com.tieto.weather.error.ServerError;
import com.tieto.weather.mapper.impl.WeatherResponseMapper;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherRequest;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVO;
import com.tieto.weather.vo.WeatherResponseVO;

/**
 * Endpoint for REST calls.
 */
@Controller
public class WeatherRESTController {
	
	private final ObjectFactory factory;
	private WeatherResponseMapper responseMapper;
	private WeatherService service;
	private CitiesVO cities;
	
	public WeatherRESTController(ObjectFactory factory) {
		this.factory = factory;
	}
		
	/**
	 * Endpoint method for getting weather data for specific city.
	 * 
	 * @param city City from URL.
	 * @return Weather data for city from request.
	 * @throws ServerError 
	 * @throws ClientError - for validation
	 */
    @RequestMapping(value="/rest/{city}", method=RequestMethod.GET)
    @ResponseBody
    public WeatherResponse getCityWeather(@PathVariable("city") String city) throws ServerError, ClientError {
            	
    	LoggerFactory.getLogger(WeatherRESTController.class).info("REST Request for city: " + city);
    	WeatherResponseVO response;
    	WeatherResponse result;
    	
    	validationCities(city);
    	
		response = new WeatherResponseVO(service.getWeatherData(city));
    	
		result = responseMapper.mapWeatherResponse(response, factory.createWeatherResponse());
		// TODO use XSD mapper also here?
		
		LoggerFactory.getLogger(WeatherRESTController.class).info("REST Request completed.");
		
    	return result; 
    }
    
    /**
     * Endpoint method for getting weather data for all supported cities.
     * 
     * @return Weather data for supported cities.
     * @throws ServerError 
     */
    @RequestMapping(value="/rest", method=RequestMethod.GET)
    @ResponseBody
    public WeatherResponse getAllWeathers() throws ServerError {
        
    	LoggerFactory.getLogger(WeatherRESTController.class).info("REST Request for all cities.");
    	
    	WeatherResponseVO response = new WeatherResponseVO();
    	WeatherResponse result;
    	
    	for (String city : cities.getCities().keySet()) {
    		response.getCityWeather().add(service.getWeatherData(city));    		
		}
    	    	
		// use XSD mapper also here?
    	result = responseMapper.mapWeatherResponse(response, factory.createWeatherResponse());
    	
    	LoggerFactory.getLogger(WeatherRESTController.class).info("REST Request completed.");
    	
    	return result;  
    }
    
	/**
	 * Validation for Cities in REST request.
	 * 
	 * @param weatherRequest
	 * @throws ClientError - for undefined city from configuration
	 */
	private void validationCities(String city) throws ClientError {
		
		if ( !cities.getCities().containsKey(city) ) {
			throw new ClientError("Validation REST request - Undefined city!");
		}
				
	}
    
    
    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(Exception ex) {
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.set("MyResponseHeader", "MyValue");
    	return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    }
    */
    
	public void setWeatherResponseMapper( WeatherResponseMapper responseMapper) {
		this.responseMapper = responseMapper;
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
}
