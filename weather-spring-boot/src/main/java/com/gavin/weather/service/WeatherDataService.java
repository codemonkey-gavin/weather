package com.gavin.weather.service;

import com.gavin.weather.entity.City;
import com.gavin.weather.entity.WeatherResponse;

public interface WeatherDataService {
    WeatherResponse getWeatherDataByCityId(String id);
    WeatherResponse getWeatherDataByCityName(String name);
    void syncWeatherDataByCity(City city);
}
