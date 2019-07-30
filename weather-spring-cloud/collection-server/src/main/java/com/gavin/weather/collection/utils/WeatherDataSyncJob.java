package com.gavin.weather.collection.utils;

import com.gavin.weather.collection.entity.City;
import com.gavin.weather.collection.service.CityClient;
import com.gavin.weather.collection.service.WeatherDataService;
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
    private CityClient cityClient;
    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<City> list = null;
        list = cityClient.listCity();
        for (City city : list) {
            logger.info("开始执行天气数据同步服务-" + city.getName() + "[" + city.getId() + "]");
            weatherDataService.syncWeatherDataByCityId(city.getId());
        }
    }
}
