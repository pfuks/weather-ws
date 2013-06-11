package com.tieto.weather.mapper;

public interface Mapper<S, T> {

    T map(S source, T target);   
        
}