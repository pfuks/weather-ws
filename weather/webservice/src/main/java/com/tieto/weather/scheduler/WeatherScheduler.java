package com.tieto.weather.scheduler;

import com.tieto.weather.service.WeatherService;
import com.tieto.weather.vo.CitiesVO;

public class WeatherScheduler {
	
	private WeatherService service;
	private CitiesVO cities;

	public void readWeathers() {
		for (String city : cities.getCities().keySet()) {
			try {
				System.out.println("Scheduled." + city);
				service.updateWeatherData(city);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
}
