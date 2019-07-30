package com.gavin.weather.data.service;

import com.gavin.weather.data.entity.WeatherResponse;

public interface WeatherDataService {
    /**
     * 根据城市ID获取天气数据
     *
     * @param cityId
     */
    WeatherResponse getWeatherDataByCityId(String cityId);
}
