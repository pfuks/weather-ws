package com.tieto.weather.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tieto.weather.mapper.Mapper;
import com.tieto.weather.schema.ObjectFactory;
import com.tieto.weather.schema.WeatherResponse;
import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVO;
import com.tieto.weather.vo.WeatherResponseVO;

@Controller
public class WeatherRESTController {
	
	private final ObjectFactory factory;
	private Mapper<WeatherResponseVO,WeatherResponse> responseMapper;
	private WeatherService service;
	private CitiesVO cities;
	
	public WeatherRESTController(ObjectFactory factory) {
		this.factory = factory;
	}
		
    @RequestMapping(value="/rest/{city}", method=RequestMethod.GET)
    @ResponseBody
    public WeatherResponse getCityWeather(@PathVariable("city") String city) {
            	
    	WeatherResponseVO response = null;
		try {
			response = new WeatherResponseVO(service.getWeatherData(city));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		// TODO use XSD mapper also here?
    	return responseMapper.map(response, factory.createWeatherResponse()); 
    }
    
    @RequestMapping(value="/rest", method=RequestMethod.GET)
    @ResponseBody
    public WeatherResponse getAllWeathers() {
        
    	WeatherResponseVO response = new WeatherResponseVO();
    	for (String city : cities.getCities().keySet()) {
    		try {
    			response.getCityWeather().add(service.getWeatherData(city));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	
		// use XSD mapper also here?
    	return responseMapper.map(response, factory.createWeatherResponse()); 
    }
    
    
	public void setWeatherResponseMapper( Mapper<WeatherResponseVO,WeatherResponse> responseMapper) {
		this.responseMapper = responseMapper;
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
}
