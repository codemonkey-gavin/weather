package com.gavin.weather.service.Impl;

import com.gavin.weather.entity.City;
import com.gavin.weather.entity.CityList;
import com.gavin.weather.service.CityDataService;
import com.gavin.weather.utils.XmlBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() {
        try {
            //读取XML文件
            Resource resource = new ClassPathResource("citylist.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            //XML转为对象
            CityList list = (CityList) XmlBuilder.xmlStrToObject(CityList.class, sb.toString());
            return list.getCityList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
