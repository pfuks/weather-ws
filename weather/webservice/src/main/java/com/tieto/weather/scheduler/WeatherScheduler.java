package com.tieto.weather.scheduler;

import com.tieto.weather.service.WeatherService;

public class WeatherScheduler {
	
	private WeatherService service;

	public void readWeathers() {
		
		try {
			System.out.println("Scheduled.");
			service.updateWeatherData("Bohumin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
		
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}
}
