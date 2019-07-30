package com.gavin.weather.utils;

import com.gavin.weather.entity.City;
import com.gavin.weather.service.CityDataService;
import com.gavin.weather.service.WeatherDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<City> list = null;
        list = cityDataService.listCity();
        for (City city : list) {

            logger.info("开始执行天气数据同步服务-" + city.getName() + "[" + city.getId() + "]");
            weatherDataService.syncWeatherDataByCity(city);
        }
    }
}
