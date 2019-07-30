package com.gavin.weather.data.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavin.weather.data.entity.WeatherResponse;
import com.gavin.weather.data.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getWeatherDataByCityId(String cityId) {
        String uri = WEATHER_URL + "?citykey=" + cityId;
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse wr = null;
        String body = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(uri)) {
            logger.info("Redis has data,key=" + uri);
            body = ops.get(uri);
        } else {
            logger.info("Redis don't has data!");
            // 缓存没有，抛出异常
            throw new RuntimeException("Don't has data!");
        }

        try {
            wr = mapper.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wr;
    }
}
