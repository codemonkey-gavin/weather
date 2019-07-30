package com.gavin.weather.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavin.weather.entity.City;
import com.gavin.weather.entity.WeatherResponse;
import com.gavin.weather.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
    public WeatherResponse getWeatherDataByCityId(String id) {
        String uri = WEATHER_URL + "?citykey=" + id;

        WeatherResponse wr = this.doGetWeather(uri);
        return wr;
    }

    @Override
    public WeatherResponse getWeatherDataByCityName(String name) {
        String uri = WEATHER_URL + "?city=" + name;
        WeatherResponse wr = this.doGetWeather(uri);
        return wr;
    }

    @Override
    public void syncWeatherDataByCity(City city) {
        String uri = WEATHER_URL + "?citykey=" + city.getId();
        String uriname = WEATHER_URL + "?city=" + city.getName();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String body = null;
        ResponseEntity<String> re = restTemplate.getForEntity(uri, String.class);
        // 成功
        if (re.getStatusCodeValue() == 200) {
            body = re.getBody();
        }
        ops.set(uri, body, TIME_OUT, TimeUnit.MINUTES);
        ops.set(uriname, body, TIME_OUT, TimeUnit.MINUTES);
    }


    private WeatherResponse doGetWeather(String uri) {
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse wr = null;
        String body = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(uri)) {
            logger.info("存在缓存" + uri);
            body = ops.get(uri);
        } else {
            ResponseEntity<String> re = restTemplate.getForEntity(uri, String.class);
            // 成功
            if (re.getStatusCodeValue() == 200) {
                body = re.getBody();
            }
            ops.set(uri, body, TIME_OUT, TimeUnit.MINUTES);
        }

        try {
            wr = mapper.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wr;
    }
}
