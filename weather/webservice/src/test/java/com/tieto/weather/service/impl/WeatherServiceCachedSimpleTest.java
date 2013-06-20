package com.tieto.weather.service.impl;

import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class,
locations = {"classpath:/weather-web-service.xml"})
public class WeatherServiceCachedSimpleTest {

}
