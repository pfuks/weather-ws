package com.tieto.weather.scheduler;

import com.tieto.weather.error.ServerError;
import com.tieto.weather.service.WeatherServiceCached;
import com.tieto.weather.vo.CitiesVO;

/**
 * Scheduler class used for periodical refresh of weather cache.
 */
public class WeatherScheduler {
	
	private WeatherServiceCached weatherService;
	private CitiesVO cities;
	
	public WeatherScheduler(WeatherServiceCached weatherService, CitiesVO cities) throws ServerError {
		this.weatherService = weatherService;
		this.cities = cities;
		// to be able to load everything before context is initialized 
		readWeathers();
	}

	/**
	 * Method used for refetch weather data cache for supported cities.
	 * 
	 * @throws ServerError
	 */
	public void readWeathers() throws ServerError {
		for (String city : cities.getCities().keySet()) {
			weatherService.updateWeatherData(city);
		}
	}
}
