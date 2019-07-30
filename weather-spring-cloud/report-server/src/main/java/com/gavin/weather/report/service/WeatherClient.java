package com.gavin.weather.report.service;

import com.gavin.weather.report.entity.City;
import com.gavin.weather.report.entity.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@FeignClient(name = "weather-eureka-zuul-server", fallback = WeatherClientFallBack.class)
public interface WeatherClient {
    @GetMapping("/data/weather/cityid/{cityid}")
    WeatherResponse getWeatherData(@PathVariable("cityid") String cityid);

    @GetMapping("/city/city/list")
    List<City> listCity();
}

