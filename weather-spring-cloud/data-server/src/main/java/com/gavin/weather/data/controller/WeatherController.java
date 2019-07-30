package com.gavin.weather.data.controller;

import com.gavin.weather.data.entity.WeatherResponse;
import com.gavin.weather.data.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;

    @RequestMapping("/cityid/{cityid}")
    public WeatherResponse getDataByCityId(@PathVariable("cityid") String cityid) {
        return weatherDataService.getWeatherDataByCityId(cityid);
    }
}
