package com.tieto.weather.vo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class CityValidator implements Validator {
	
	private CitiesVO cities;
	

	public boolean supports(Class<?> clazz) {
		return String.class.equals(clazz);
	}


	public void validate(Object target, Errors errors) {
		
		String city = (String) target;
				
		if ( !cities.getCities().containsKey(city) ) {
			errors.reject("Undefined city");
		}
		
		
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}

}
