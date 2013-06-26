package com.tieto.weather.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CitiesValidator implements Validator {
	
	private CitiesVO cities;

	public boolean supports(Class<?> clazz) {
		return ArrayList.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		@SuppressWarnings("unchecked")
		List<String> citiesList = (ArrayList<String>)target;
		
		for (String city : citiesList) {
			if ( !cities.getCities().containsKey(city) ) {
				errors.reject("Undefined city");
			}
		}
		
	}
	
	public void setCities(CitiesVO cities) {
		this.cities = cities;
	}
		
}
