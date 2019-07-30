package com.gavin.weather.city.controller;

import com.gavin.weather.city.entity.City;
import com.gavin.weather.city.service.CityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityDataService cityDataService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<City> getCityList() {
        return cityDataService.listCity();
    }
}
