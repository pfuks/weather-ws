package com.tieto.weather.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.mapper.impl.WeatherResponseMapper;
import com.tieto.weather.schema.ObjectFactory;
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
	 */
    @RequestMapping(value="/rest/{city}", method=RequestMethod.GET)
    @ResponseBody
    public WeatherResponse getCityWeather(@PathVariable("city") String city) throws ServerError {
            	
    	
    	WeatherResponseVO response = null;
		response = new WeatherResponseVO(service.getWeatherData(city));
    	
		// TODO use XSD mapper also here?
    	return responseMapper.mapWeatherResponse(response, factory.createWeatherResponse()); 
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
        
    	WeatherResponseVO response = new WeatherResponseVO();
    	for (String city : cities.getCities().keySet()) {
    		response.getCityWeather().add(service.getWeatherData(city));    		
		}
    	
    	return responseMapper.mapWeatherResponse(response, factory.createWeatherResponse()); 
    }
    
    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(Exception ex) {
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.set("MyResponseHeader", "MyValue");
    	return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    }
    */
    
	public void setWeatherResponseMapper(  WeatherResponseMapper responseMapper) {
		this.responseMapper = responseMapper;
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
}
