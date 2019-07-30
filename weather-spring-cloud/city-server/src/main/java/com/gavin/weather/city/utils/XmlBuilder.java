package com.gavin.weather.city.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

public class XmlBuilder {
    public static Object xmlStrToObject(Class<?> classt, String xmlStr) throws Exception {
        Object obj = null;
        Reader reader = null;

        JAXBContext context = JAXBContext.newInstance(classt);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        obj = unmarshaller.unmarshal(reader);
        if (null != reader) {
            reader.close();
        }
        return obj;
    }
}
