package com.tieto.weather.mapper;

/**
 * Weather mapper interface.
 *
 * @param <S> Source class containing data to be mapped.
 * @param <T> Target class for filling data from source class.
 */
public interface Mapper<S, T> {

    /**
     * Mapping method.
     * 
     * @param source Source object containing data to be mapped.
     * @param target Target object for filling data from source.
     * @return Filled target object.
     */
    T map(S source, T target);   
        
}