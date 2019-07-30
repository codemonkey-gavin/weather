package com.gavin.weather.collection.service;

public interface WeatherDataService {
    /**
     * 根据城市ID同步天气数据
     *
     * @param cityId
     */
    void syncWeatherDataByCityId(String cityId);
}
