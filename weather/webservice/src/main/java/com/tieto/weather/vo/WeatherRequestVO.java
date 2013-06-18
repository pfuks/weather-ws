package com.tieto.weather.vo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Value object for storing request.
 */
public class WeatherRequestVO {
	
	private Collection<String> cities;

	public WeatherRequestVO() {
		this.cities = new ArrayList<String>();
	}	
	
	public Collection<String> getCities() {
		return cities;
	}
}
