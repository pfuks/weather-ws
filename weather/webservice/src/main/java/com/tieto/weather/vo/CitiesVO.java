package com.tieto.weather.vo;

import java.util.Map;

/**
 * Value object for storing cities from properties.
 */
public class CitiesVO {
	
	private Map<String, String> cities;
	
	public CitiesVO(Map<String, String> map) {
		this.cities = map;
	}
	
	public Map<String, String> getCities() {
		return this.cities;
	}
}
