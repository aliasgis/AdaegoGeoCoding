package com.jygis.geocoding;
import com.jygis.geocoding.GeoModel.JusoInfo;
import com.jygis.geocoding.GeoModel.JusoService;
import com.jygis.geocoding.spatial.GeoAddr;
import com.jygis.geocoding.spatial.GeoCentroid;
import com.jygis.geocoding.spatial.GeoDataStore;
import com.vividsolutions.jts.geom.Point;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GeoCoding {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private  GeoDataStore geo;

    @Autowired
    private GeoCentroid g;

    @Autowired
    private GeoAddr address;

    @Autowired
    private JusoService service;




    public String AddrToXY(String addr) {

        int cnt = service.getCount(addr);
        Double x = 0.0;
        Double y =0.0;
        Map result = new HashMap<String, String>();
        String Query =null;
        ShapefileDataStore dataStore;
        SimpleFeatureSource  fc = null;
        SimpleFeature fea = null;
        try {

            if(cnt<1) {
                dataStore = geo.GetFeatureDataStore();
                fc = dataStore.getFeatureSource();
                Query = "jibun='" + addr + "'";

                System.out.println(Query);
                System.out.println("갯수:"+cnt);
                //Point pt= geometry.getCentroid();
                Point pt = g.GeoCenter(fc, Query);

                //int count = features.size();
                 x = pt.getX();
                 y = pt.getY();

                result.put("x", x);
                result.put("y", y);

                dataStore.dispose();
                JusoInfo juso = new JusoInfo();
                juso.setAddress(addr);
                juso.setX(x);
                juso.setY(y);
                service.registerJuso(juso);

                //service.registerJuso();

            } else {
                Map res = new HashMap<String, Double>();
                res = service.getGeoXY(addr);

              //  result.put("x", res.));
                result.put("x",res.get("x"));
                result.put("y", res.get("y"));
            }
            //pt=null;
            result .entrySet();
        } catch (IOException | CQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result.values().toString();
    }

    public String XYToADDR(Double X, Double Y) {

        Map result = new HashMap<String, String>();
        String Query =null;
        ShapefileDataStore dataStore;
        SimpleFeatureSource  fc = null;
        SimpleFeature fea = null;
        String addr =null;
        try {

            dataStore = geo.GetFeatureDataStore();
            fc = dataStore.getFeatureSource();
            Query = "Intersects(the_geom,POINT("+X+" "+Y+"))";

            //Point pt= geometry.getCentroid();
             addr = address.GeoCenter(fc,Query);

            result.put("adress", addr);

            dataStore .dispose();

            //pt=null;
            result .entrySet();
        } catch (IOException | CQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result.values().toString();
    }
}
