package com.gavin.weather.report.controller;

import com.gavin.weather.report.entity.City;
import com.gavin.weather.report.entity.WeatherResponse;
import com.gavin.weather.report.service.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/report")
public class WeatherController {
    @Autowired
    private WeatherClient weatherClient;

    @RequestMapping("/cityid/{cityid}")
    public ModelAndView getDataByCityId(@PathVariable("cityid") String cityid, Model model) {
        List<City> list = weatherClient.listCity();
        WeatherResponse response = weatherClient.getWeatherData(cityid);
        model.addAttribute("title", "天气预报");
        model.addAttribute("cityId", cityid);
        model.addAttribute("cityList", list);
        model.addAttribute("report", response.getData());
        return new ModelAndView("weather/report", "reportModel", model);
    }
}
