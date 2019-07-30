package com.gavin.weather.report.service;

import com.gavin.weather.report.entity.City;
import com.gavin.weather.report.entity.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherClientFallBack implements WeatherClient {
    @Override
    public WeatherResponse getWeatherData(String cityid) {
        WeatherResponse response=new WeatherResponse();
        response.setStatus("-1");
        response.setDesc("获取天气数据失败");
        response.setData(null);
        return response;
    }

    @Override
    public List<City> listCity() {
        List<City> list = new ArrayList<City>();
        City model = new City();
        model.setId("101280101");
        model.setName("广州");
        model.setCode("guangzhou");
        model.setProvince("广东");
        list.add(model);
        return list;
    }
}
