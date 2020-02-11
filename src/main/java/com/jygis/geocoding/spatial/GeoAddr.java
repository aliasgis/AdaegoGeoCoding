package com.jygis.geocoding.spatial;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class GeoAddr {
    public String GeoCenter(SimpleFeatureSource fc,String Query) throws IOException, CQLException {
        //Point pt=null;
        String addr =null;
        Filter filter = CQL.toFilter(Query);

        // Filter filter = CQL.toFilter("strConcat(A3,A4)="+EMD+BONBU+"'");
        //Filter filter = CQL.toFilter("jibun='"+Addr+"'");
        SimpleFeatureCollection features = fc.getFeatures(filter);
        SimpleFeatureIterator iterator= features.features();
        if(iterator.hasNext()==true) {
            SimpleFeature feature = (SimpleFeature) iterator.next();
            addr = feature.getAttribute("jibun").toString();
        } else {
            addr ="해당사항없음";
        }

        iterator.close();
        return addr;
    }


}
