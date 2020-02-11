package com.jygis.geocoding.GeoModel;

import com.jygis.geocoding.mapper.JusoMapper;
import com.jygis.geocoding.GeoModel.JusoInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class JusoService {

    @Resource
    private JusoMapper jusoMapper;


    public int getCount(String address) {
        return jusoMapper.count(address);
    }
    public Map<String, Double> getGeoXY(String address) {
        return  jusoMapper.getGeoXY(address);
    }

    public void registerJuso(JusoInfo jusoInfo) {

        jusoMapper.registerJuso(jusoInfo);
    }
}
