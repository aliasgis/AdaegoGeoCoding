package com.jygis.geocoding.mapper;

import com.jygis.geocoding.GeoModel.JusoInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JusoMapper {
    int count(String address);
    Map<String, Double> getGeoXY(String address);
    void registerJuso(JusoInfo jusoInfo);
}