package com.gavin.weather.collection.service;

import com.gavin.weather.collection.entity.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient("weather-city-server")
public interface CityClient {
    @GetMapping("/city/list")
    List<City> listCity();
}
