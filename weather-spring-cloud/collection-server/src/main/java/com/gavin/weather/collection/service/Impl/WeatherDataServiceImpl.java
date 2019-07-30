package com.gavin.weather.collection.service.Impl;

import com.gavin.weather.collection.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini";
    private final int TIME_OUT = 30;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void syncWeatherDataByCityId(String cityId) {
        String uri = WEATHER_URL + "?citykey=" + cityId;

        String body = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ResponseEntity<String> re = restTemplate.getForEntity(uri, String.class);
        // 成功
        if (re.getStatusCodeValue() == 200) {
            body = re.getBody();
            logger.info("获取天气数据成功！{}", uri);
        }
        ops.set(uri, body, TIME_OUT, TimeUnit.MINUTES);
    }
}
