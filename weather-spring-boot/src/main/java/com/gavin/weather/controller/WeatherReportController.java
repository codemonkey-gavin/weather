package com.gavin.weather.controller;

import com.gavin.weather.service.CityDataService;
import com.gavin.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherDataService weatherDataService;

    @RequestMapping("/cityid/{cityid}")
    public ModelAndView getDataByCityId(@PathVariable("cityid") String cityid, Model model) {
        model.addAttribute("title","天气预报");
        model.addAttribute("cityId",cityid);
        model.addAttribute("cityList",cityDataService.listCity());
        model.addAttribute("report",weatherDataService.getWeatherDataByCityId(cityid).getData());
        return new ModelAndView("weather/report","reportModel",model);
    }
}
