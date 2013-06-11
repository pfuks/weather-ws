package com.tieto.weather.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tieto.weather.vo.CityWeatherVO;
import com.tieto.weather.vo.WeatherResponseVO;

@Controller
public class WeatherRESTController {
		
    @RequestMapping(value="/{city}", method=RequestMethod.GET)
    @ResponseBody
    public WeatherResponseVO getCityWeather(@PathVariable("city") String city) {
        
    	System.out.println("Hello from REST!! GET" + city );
    	
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
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public void getAllWeathers() {
        
    	System.out.println("Hello from REST!! ALL cities");

        //return user;
    }   

}
