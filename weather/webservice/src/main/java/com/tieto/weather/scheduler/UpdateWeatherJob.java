package com.tieto.weather.scheduler;

import org.slf4j.LoggerFactory;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.service.WeatherServiceCached;
import com.tieto.weather.vo.CitiesVOFactory;

/**
 * Updating class used for periodical refresh of weather cache.
 */
public class UpdateWeatherJob {
	
	private WeatherServiceCached weatherService;
	private CitiesVOFactory cities;
	
	public UpdateWeatherJob(WeatherServiceCached weatherService, CitiesVOFactory cities) throws ServerError {
		this.weatherService = weatherService;
		this.cities = cities;
	}

	/**
	 * Method used for refetch weather data cache for supported cities.
	 * 
	 * @throws ServerError
	 */
	public void readWeathers() {
		
		for (String city : cities.getCities().keySet()) {
			try {
				weatherService.updateWeatherData(city);
			} catch (ServerError ex) {
				LoggerFactory.getLogger(UpdateWeatherJob.class).error("Call Wunderground FAILED! for: " + city);
			}					
		}
	}
}
